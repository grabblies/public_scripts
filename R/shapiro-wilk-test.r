#############################
# Shapiro-Wilk test
# Given a linear model "model" and a single data vector "data_vector", swtest simulates normal 
# random variables based on this data, and tests for normality of the model's residuals based on these
# values. If p is less than the chosen alpha level, then the null hypothesis (that the residuals are normally 
# distributed), is rejected. 
swtest = function(model, data_vector, sims) {
  n = length(model$residuals)
  m = sims # number of simulations
  data_vector = sort(data_vector)
  z = data.frame(zs = matrix(NA, nrow = n, ncol = 1))
  R = data.frame(r = matrix(NA,nrow = m, ncol =1))
  
  #Generating z values for data
  for (j in 1:n) {
    z[j,] = qnorm((j-.375)/(n+.25), mean = 0, sd = 1)
  }
  
  #Computing r from data
  sorted_residuals = data.frame(a = sort(model$residuals))
  r_from_data = sum(z*sorted_residuals)/sqrt(sum(z^2)*sum(sorted_residuals^2))
  
  #Running trials, generating r value for each trial
  for (i in 1:m) {
    sim_set = data.frame(cbind(a = rnorm(n), b = data_vector))
    res = data.frame(eis = sort(residuals(lm(a ~ b, data = sim_set))))
    r_star = sum(z*res)/sqrt(sum(z^2)*sum(res^2))
    R[i,] = r_star
  }
  R = sort(R[,1])
  p = length(which(R < r_from_data))/m
  return(p)
}
############################