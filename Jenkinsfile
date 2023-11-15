
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
             
                 
  stage('Docker Image build'){

                              steps{

                                  script{

                                      sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                                      sh 'docker image tag  $JOB_NAME:v1.$BUILD_ID tasnim12/$JOB_NAME:v1.$BUILD_ID'
                                      sh 'docker image tag  $JOB_NAME:v1.$BUILD_ID tasnim12/$JOB_NAME:latest'

                                  }
                              }
                          }
                   stage('push image to dockerhub'){

                              steps{

                                  script{

                                      withCredentials([string(credentialsId: 'git_creds', variable: 'docker_hub_cred')]) {
                                      sh 'docker login -u tasnim12 -p ${docker_hub_cred}'
                                      sh 'docker image push  tasnim12/$JOB_NAME:v1.$BUILD_ID'
                                      sh 'docker image push  tasnim12/$JOB_NAME:latest'
                  }

                                  }
                              }
                          }


}


}
