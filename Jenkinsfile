
pipeline{

    agent any

    stages {

     stage('Git Checkout'){

            steps{

                script{

                    git branch: 'tasnim', url: 'https://github.com/khchimi-Othmen/ski-devops.git'
                }
            }
        }
       
        stage('Maven build'){

            steps{

                script{

                    sh 'mvn clean compile'
                }
            }
        }
       
  stage('Static code analysis'){

            steps{

                script{

                    withSonarQubeEnv(credentialsId: 'SONARQUBE-TOKEN') {

                        sh 'mvn sonar:sonar'
                    }
                   }

                }
            }
             


        stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
            }
        }

        
        
}
}
