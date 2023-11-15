pipeline {


    agent any
    stages{
        stage('Checkout GIT'){
         steps {
             echo 'Pulling ...';
              git branch: 'mohamed_chebbi',
              url : 'https://github.com/khchimi-Othmen/ski-devops.git'
         } 

        }



        stage('Testing maven'){
            steps {
                sh """mvn -version """
            }

        }
        stage('MVN CLEAN'){
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN COMPILE'){
            steps {
                sh 'mvn compile'
            }
        }
		stage('SonarQube analysis 1') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=hamza'
            }
        }
        stage('JUnit and Mockito Test'){
            steps{
                script
                {
                    if (isUnix())
                    {
                        sh 'mvn --batch-mode test'
                    }
                    else
                    {
                        bat 'mvn --batch-mode test'
                    }
                }
            }
        }        

          stage('MVN Nexus'){
            steps {
                sh 'mvn deploy'
            } 
            }         
        stage('Building image docker-compose') {
          steps {

              sh "docker-compose up -d"
          }
        }

         stage('Build image') {
          steps {
            sh "docker build -t chebbi404/imagedevops ."
       		}
       		}
    		
 	   stage('Push image') {
 	    steps {
 	       withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
 			
        	 sh "docker push chebbi404/imagedevops"
        	}
        	}
        	}


       stage('Cleaning up') {
 	    steps {
 	       withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
 			
        	 sh "docker rmi -f chebbi404/imagedevops"
        	}
        	}
        	}


	  stage('Sending email'){
	    steps {
	        mail bcc: '', body: '''Hello from hamza lassoued,
	        Devops Pipeline with success.
	        Cordialement''', cc: '', from: '', replyTo: '', subject: 'Devops', to: 'hamza.lassoued@esprit.tn'
	             }
	        }

    }
}
