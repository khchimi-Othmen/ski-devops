
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
        stage('Maven install'){

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
                                classifier: '', file:'target/Ski.jar',
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
                 

}
}
