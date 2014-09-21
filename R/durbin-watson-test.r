
############################################
#Computes p value (likelihood of model's D value under hypothesis of no autocorrelation)
dwtest = function(model, data_vector) {
  m = 10000 # number of trials
  #data_vector = d1$year
  n = length(data_vector)
  D_thing = data.frame(d = matrix(NA,nrow = m, ncol =1))
  for (i in 1:m) {
    sim_set = data.frame(cbind(a = rnorm(n), b = data_vector))
    sim_model = lm(a ~ b, data=sim_set)
    D_thing[i,] = gen_D(sim_model)
  }
  #D_thing
  D_thing = sort(D_thing[,1])
  D_data = gen_D(model)
  p = length(which(D_thing < D_data))/m
  return(p)
}

############################################

#This function generates the D value for a given model
gen_D = function(model) {
  D = 0
  res = data.frame(model$residuals)
  n = dim(res)[1]
  z = 0
  for (i in 2:n) {
    z = z + (res[i,] - res[i-1,])^2
  }
  D = z/sum(res^2)
  return(D)
}
############################################