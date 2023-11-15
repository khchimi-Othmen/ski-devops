
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
             



 stage('Build image') {
            steps {
                sh 'docker build -t tasnim12/registration:1.0.0 .'
            }
        }
        
        
        
      stage('Push image in Docker Hub') {
            steps {
                sh "docker login -u tasnim12 -p tasnim123456"
                sh "docker push tasnim12/registration:1.0.0"
            }
        }
        stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
            }
        }

        
        
}
}
