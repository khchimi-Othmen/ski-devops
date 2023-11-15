
pipeline{

    agent any

    stages {

        stage('UNIT testing'){

            steps{

                script{

                    sh 'mvn test'
                }
            }
        }
        stage('Integration testing'){

            steps{

                script{

                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }
        stage('Maven build'){

            steps{

                script{

                    sh 'mvn clean install'
                }
            }
        }
  stage('Static code analysis'){

            steps{

                script{

                    withSonarQubeEnv(credentialsId: 'SONARQUBE-TOKEN') {

                        sh 'mvn clean package sonar:sonar'
                    }
                   }

                }
            }
              stage('upload to nexus'){

                            steps{

                                script{

                                nexusArtifactUploader artifacts: [
                                [
                                artifactId: 'gestion-station-ski',
                                classifier: '', file:'target/SKI.jar',
                                type: 'jar'
                                ]
                                ],
                                credentialsId: 'nexus-auth',
                                groupId:'tn.esprit.spring',
                                nexusUrl: '192.168.1.3:8081',
                                nexusVersion: 'nexus3',
                                protocol: 'http',
                                repository: 'maven-central-repository',
                                version: '1.0'                    }
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