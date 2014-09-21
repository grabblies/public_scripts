#DPS Data analysis

#data = read.csv("C:/Users/b/Google Drive/NC Prisoner Legal Services/parole_data_export.csv")
#data = read.csv("C:/Users/bgdavis/Google Drive/NC Prisoner Legal Services/parole_data_export.csv")
data = read.csv("/Users/b/Google Drive/NC Prisoner Legal Services/parole_data.export.csv")
i = sapply(data, is.factor)
data[i] = lapply(data[i], as.character)

data$sex[which(data$sex == "FEMALE                        ")] = "FEMALE"
data$sex[which(data$sex == "MALE                          ")] = "MALE"
data$sex = as.factor(data$sex)

data$race[which(data$race == "BLACK                         ")] = "BLACK"
data$race[which(data$race == "WHITE                         ")] = "WHITE"
data$race[which(data$race == "ASIAN/ORTL                    ")] = "ASIAN"
data$race[which(data$race == "INDIAN                        ")] = "INDIAN"
data$race[which(data$race == "UNKNOWN                       ")] = "UNKNOWN"

data$crimetype = "0"
data$crimetype[which(data$crime %in% c("HABITUAL FELON                ", "FELON UNKNOWN"))]                  = "OTHER"
data$crimetype[which(data$crime %in% c("ARSON 1ST DEGREE              ", "ARSON 2ND DEGREE              ", 
                                       "BURGLARY 1ST DEGREE           ", "BURGLARY 2ND DEGREE           "))] = "PROPERTY"
data$crimetype[which(data$crime %in% c("TRAFFICK OPIUM/HEROIN 28 GRAMS", "TRAFFICKING CONTROLLED SUBSTAN",
                                       "TRAFFICKING SCHEDULE II       "))]                                   = "DRUG"
data$crimetype[which(data$crime %in% c("ARMED ROBBERY                 ","AWDWWITKISI                   ",
                                       "MURDER SECOND DEGREE          ","ROBBERY W/DANGEROUS WEAPON    ",
                                       "AWDWISI                       ", "MURDER FIRST DEGREE           ",
                                       "KIDNAPPING 1ST DEGREE         ", "KIDNAPPING 2ND DEGREE         "))] = "VIOLENT"
data$crimetype[which(data$crime %in% c("RAPE FIRST DEGREE             ", "RAPE LESS THAN AGE 13         ",
                                       "RAPE SECOND DEGREE            ", "SEXUAL OFF 1ST DEGR W/CHILD   ",
                                       "SEXUAL OFFENSE 1ST DEGREE     ", "SEXUAL OFFENSE 2ND DEGREE     "))] = "SEX"


data$race = as.factor(data$race)
data$escapee = as.factor(data$escapee)
data$priors = as.factor(data$priors)
data$paroleanalyst = as.factor(data$paroleanalyst)
data$crime = as.factor(data$crime)
data$crimetype = as.factor(data$crimetype)

data2 = data.frame(data[,c("sex","race","age","crime","crimetype","escapee","priors", "paroleanalyst", "age_at_movement",
                           "infraction_coef","derived_lengthofinc","parole")])

data2$infraction_coef = data2$infraction_coef/(data2$derived_lengthofinc/365)
                      
## LOOKING AT WHITE/BLACK SEPERATELY
# modeling the races separately, then creating a data set where each group is held at the joint average, 
# then predicting probability of parole of different races based shared mean data but models of separate groups

wb = data2[data2$race %in% c("WHITE","BLACK"),]
wb$race = factor(wb$race)
wb$crime = factor(wb$crime)
wb$crimetype = factor(wb$crimetype)
men = data2[data2$sex == "MALE",]
wbmen = wb[wb$sex == "MALE",]
wbmen2 = wbmen[wbmen$crimetype != "DRUG",]

#white = data2[data2$race == "WHITE",] #need to remove unused levels in white$crime for model prediction to function
white = men[men$race == "WHITE",] #need to remove unused levels in white$crime for model prediction to function
white$crime = factor(white$crime)

#black = data2[data2$race == "BLACK",]
black = men[men$race == "BLACK",]
black$crime = factor(black$crime)




                      
#FINISHED RESCULPTING DATA
attach(data2)
parolees = data2[data2$parole == 1,]
nparolees = data2[data2$parole == 0,]

#str(data) #tells us the types of each element
model.basic = glm(parole ~ relevel(as.factor(sex),"MALE") + relevel(as.factor(race),"WHITE") + 
              relevel(as.factor(crime),"TRAFFICKING CONTROLLED SUBSTAN") + 
              relevel(as.factor(escapee),"N") + relevel(as.factor(priors),"N") + infraction_coef +
              age_at_movement + age + derived_lengthofinc, data = data2, family = binomial(link="logit"))

model.crimetype = glm(parole ~ relevel(as.factor(sex),"MALE") + relevel(as.factor(race),"WHITE") + 
              relevel(as.factor(crimetype),"DRUG") + 
              relevel(as.factor(escapee),"N") + relevel(as.factor(priors),"N") + infraction_coef +
              age_at_movement + age + derived_lengthofinc, data = data2, family = binomial(link="logit"))

model2.crimetype = glm(parole ~ relevel(as.factor(sex),"MALE") + relevel(as.factor(race),"WHITE") + 
              relevel(as.factor(crime),"FELON UNKNOWN") + relevel(as.factor(crimetype),"VIOLENT") + 
              relevel(as.factor(escapee),"N") + relevel(as.factor(priors),"N") + infraction_coef +
              age_at_movement + age + derived_lengthofinc, data = men, family = binomial(link="logit"))

model2.crimetype.men = glm(parole ~ relevel(as.factor(race),"WHITE") + 
              #relevel(as.factor(crime),"FELON UNKNOWN") + 
              relevel(as.factor(crimetype),"VIOLENT") + 
              relevel(as.factor(escapee),"N") + relevel(as.factor(priors),"N") + infraction_coef + exp + 
              age_at_movement + age + derived_lengthofinc, data = wbmen, family = binomial(link="logit"))

model2.crimetype.men2 = glm(parole ~ relevel(as.factor(race),"WHITE") + 
              #relevel(as.factor(crime),"FELON UNKNOWN") + 
              relevel(as.factor(crimetype),"VIOLENT") + 
              relevel(as.factor(escapee),"N") + infraction_coef + 
              age_at_movement + age + derived_lengthofinc, data = wbmen2, family = binomial(link="logit"))

model.analyst = glm(parole ~ sex + race + crime + escapee + priors + infraction_coef + age_at_movement + age +
              derived_lengthofinc + paroleanalyst, data = data2, family = binomial(link="logit"))

model.race_crime = glm(parole ~ sex + race*crime + escapee + priors + infraction_coef + age_at_movement + age +
              derived_lengthofinc, data = data2, family = binomial(link="logit"))

model.race_infrac = glm(parole ~ sex + race*infraction_coef + escapee + priors + crime + age_at_movement + age +
              derived_lengthofinc, data = data2, family = binomial(link="logit"))

model.sex_crime = glm(parole ~ relevel(as.factor(sex),"MALE")* + relevel(as.factor(crime),"TRAFFICKING CONTROLLED SUBSTAN") +
              relevel(as.factor(race),"WHITE") + 
              relevel(as.factor(escapee),"N") + relevel(as.factor(priors),"N") + infraction_coef +
              age_at_movement + age + derived_lengthofinc, data = data2, family = binomial(link="logit"))




#Histogram of length of incarceration
hist(nparolees$derived_lengthofinc/365, main = "Distribution of Length of Incarceration", 
        xlab = "Length of Incarceration", col=rgb(1,0,0,0.5), probability = TRUE,
        breaks = 50)
len = hist(parolees$derived_lengthofinc/365, add = T, col=rgb(0,0,1,0.5), probability = TRUE,
        breaks = 50)
legend("topright", c("Paroled","Non-Paroled"),fill = c(rgb(0,0,1,0.5),rgb(1,0,0,0.5)))
lines(density(parolees$derived_lengthofinc/365), col = "blue", lwd = 2)
lines(density(nparolees$derived_lengthofinc/365), col = "red", lwd = 2)


#Histogram of age
hist(nparolees$age, breaks = 50, main = "Distribution of Age", 
     xlab = "Age", col=rgb(1,0,0,0.5), probability = TRUE)
hist(parolees$age, breaks = 50, add = TRUE, col=rgb(0,0,1,0.5), probability = TRUE)
lines(density(parolees$age), col = "blue", lwd = 2)
lines(density(nparolees$age), col = "red", lwd = 2)
legend("topright", c("Paroled","Non-Paroled"),fill = c(rgb(0,0,1,0.5),rgb(1,0,0,0.5)))


#Histogram of infraction coef. 
hist(nparolees$infraction_coef, breaks = 150, main = "Distribution of Infraction Coef.", 
     xlab = "Infraction Coef.", col=rgb(1,0,0,0.5), probability = TRUE, xlim= c(0,250))
hist(parolees$infraction_coef, breaks = 15, add = TRUE, col=rgb(0,0,1,0.5), probability = TRUE)
#lines(density(parolees$infraction_coef), col = "blue", lwd = 2)
#lines(density(nparolees$infraction_coef), col = "red", lwd = 2)
legend("topright", c("Paroled","Non-Paroled"),fill = c(rgb(0,0,1,0.5),rgb(1,0,0,0.5)))


#Looking at log ratios
l = exp(coef(model.basic))







model.white = glm(parole ~  escapee + priors + infraction_coef + age_at_movement + age + derived_lengthofinc + crime, 
                  data = white, family = binomial(link="logit"))

model.black = glm(parole ~  escapee + priors + infraction_coef + age_at_movement + age + derived_lengthofinc + crime, 
                  data = black, family = binomial(link="logit"))

data2mean = with(data2, data.frame(sex = "MALE", escapee = "N", priors = "N", infraction_coef = median(infraction_coef), 
        age_at_movement = median(age_at_movement), age = median(age), derived_lengthofinc = median(derived_lengthofinc), 
        crime = intersect(white$crime,black$crime)   ))
data2test = with(data2, data.frame(sex = "MALE", escapee = "N", priors = "N", infraction_coef = 10, 
        age_at_movement = median(age_at_movement), age = median(age), derived_lengthofinc = median(derived_lengthofinc)+10, 
        crime = intersect(white$crime,black$crime)   ))

w.pred = predict(model.white, newdata = data2test, type = "response")
b.pred = predict(model.black, newdata = data2test, type = "response")

data2mean$w.pred = w.pred
data2mean$b.pred = b.pred
data2mean$dif = round(w.pred - b.pred,10)
data2mean 


## SEPARATE PAROLE PROBABILITY TABLES BY CRIME FOR WHITE/BLACK

white.crime_parole = addmargins(prop.table(table(white$crime, white$parole), 1))
black.crime_parole = addmargins(prop.table(table(black$crime, black$parole), 1))

comp = cbind(white.crime_parole[,1:3],black.crime_parole[,2:3])


##TABLES##

#Table of parole counts
pc = data.frame(black = c(121, 232), white = c(232, 56))
pcount = data.frame(race = c("Black","White"), total = c(dim(black)[1],dim(white)[1]), 
    paroled = c(dim(black[black$parole== 1,])[1], dim(white[white$parole== 1,])[1]) )
pcount$proportion = round(pcount$paroled/pcount$total, digits =3)

#Tables to explore relationships
sex_v_parole = addmargins(prop.table(table(sex, parole),1))
race_v_parole = addmargins(prop.table(table(race,parole),1))
crime_v_parole = addmargins(prop.table(table(crime,parole),1))



###MOSAICS


mosaicplot(wb$parole ~ wb$race, color = TRUE, dir = c("v","h"),
           main = "Parole Decisions by Race", xlab = "Parole Decision (1 = Paroled)", 
           ylab = "Race", las = 1)

###BARPLOT ON PAROLE ANALYST
barplot((summary(data2$paroleanalyst)), 
        las = 3, col = rgb(0,0,0,.2), 
        main = "Inmates and Parolees per Parole Analyst", 
        ylab = "No. of Cases")
barplot((summary(parolees$paroleanalyst)), 
        add = TRUE, col = rgb(0,0,1,.5), axisnames = FALSE)
legend("topright", c("Total","Paroled"),fill = c(rgb(0,0,0,0.2),
                                                 rgb(0,0,1,0.5)))




##Directing output
sink(file = "C:/Users/B/Google Drive/DPS PRISON DATA/R_output.txt", append = TRUE, split = TRUE, type = "output")
write.csv(coef(summary(model2.crimetype.men2)), file = "C:/Users/bgdavis/Google Drive/DPS PRISON DATA/model_stuff.csv")
