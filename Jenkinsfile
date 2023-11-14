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
      stage('Prometheus/Grafana'){
                        steps {
                         sh "docker start prometheuss"
                          sh "docker start grafana"

                        }
        }

       }
   
         post {
        failure {
            // Envoyer un e-mail en cas d'échec de la construction
            emailext subject: "Build failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                      body: """<p>Build ${env.JOB_NAME} [${env.BUILD_NUMBER}] failed.</p>
                               <p>See the console output for more information:<a href='http://192.168.1.20:8080/job/devski/${env.BUILD_NUMBER}/console'>${env.BUILD_URL}console</a></p>""",
                      to: 'emnagharbia6@gmail.com',
                      mimeType: 'text/html'
        }
        success {
            // Envoyer un e-mail en cas de réussite de la construction
            emailext subject: "Build successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                      body: """<p>Build ${env.JOB_NAME} [${env.BUILD_NUMBER}] was successful.</p>
                               <p>See the console output for more information:<a href='http://192.168.1.20:8080/job/devski/${env.BUILD_NUMBER}/console'>${env.BUILD_URL}console</a></p>""",
                      to: 'emnagharbia6@gmail.com',
                      mimeType: 'text/html'
        }
    }


}
  
