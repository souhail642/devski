pipeline {
environment {
     dockerImageName = "ski"
     dockerComposeFilePath = "docker-compose.yml"
    }

    agent any
    stages {
         stage ('Checkout GIT'){
                steps {
                    echo 'pulling... ';
                    git branch :'EmnaGharbia',
                    url : 'https://github.com/souhail642/devski.git';
                }
            }
               stage('Maven Version') {
            steps {
                sh 'mvn -version'
            }
        }
        stage('JDK Version') {
            steps {
                sh 'java -version'
        }}
     stage('MVN Clean Compile') {
            steps {
                sh 'mvn clean compile'
        }}
         stage('JaCoCo Coverage') {
                    steps {
                        script {

                            // No need to use 'dir' if the pom.xml is in the root directory
                            sh 'mvn jacoco:report -DskipTests'

                    }
                }}
       
      stage("SonarQube analysis'") {
           steps {
              withSonarQubeEnv('sq1') {
              sh 'mvn test'
               sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.20:9000/ -Dsonar.login=sqa_cd57e758bcd73fe6a2753e3d82caa12a657da93a"

             }
                      }
    }
 stage('Unit  Testing using Mockito & JUnit') {
            steps {

                    sh 'mvn test'

        }}



        stage("Artifact construction") {
            steps {
                script {

                    sh "mvn package -DskipTests=true"

            }
        }}
        stage('Publish to Nexus') {
            steps {

                echo 'Deploying to Nexus'
                sh 'mvn clean deploy -Dmaven.test.skip=true'

        }}




         stage('Building our image') {
               steps{
           script{
                         sh "docker build -t ${dockerImageName} ."
                     }
               }

      }

stage('docker push'){
      steps{
     script{
                  sh 'docker login -u emnagharbia -p Emna50217381.'
                  sh 'docker tag ski emnagharbia/gestion-station-ski'
                   sh 'docker push emnagharbia/gestion-station-ski'
            }

        }

                  }



    stage("docker compose")
           {
             steps{


                 script{

                     sh "docker-compose up -d"


                   }

               }
             }


       }
   }
