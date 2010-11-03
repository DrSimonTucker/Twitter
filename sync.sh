mvn clean javadoc:javadoc
rsync -avz --progress target/site/apidocs/ stulogin.dcs.shef.ac.uk:public_html/twitter/