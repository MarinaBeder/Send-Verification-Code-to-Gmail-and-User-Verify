# Send-Verification-Code-to-Gmail-and-User-Verify
## Java (Spring boot Framework)
### When the user makes registration using email
###        We check if the user already exists or not
####            if the user doesn't exist we send for user verification code to the user's email --> we send to the user "We send a link to your email, Please verify it"
####            if the user exists  but the user doesn't verify (his/her) verification code -->we send to the user "Please verify your email"
####            if the user exists and verifies (his/her) email -->we send for user "Email already exists"

### ----- If you want to try this code on localhost -----
#### Go to application properties : 
### -** in part Database Properties **-
####                               1- put your database name
####                               2- put username of database
####                               3- put password of database
#### Go to application properties : 
### -** Connect to smtp server **-
####                               1- put username
####                               2- put password
#### Go to SendVerificationCodeService class :
                    helper.setFrom("{ put gmail that will send message }",senderName);
                    
                    *note String verifyURL = siteURL + "http://localhost:8080/verify?code=" + user.getVerficationCode();
                    you can change the port {8080} according to the port you will use
