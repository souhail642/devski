pipeline {

    agent any
    stages {
         stage ('Checkout GIT'){
                steps {
                    echo 'pulling... ';
                        git branch : 'RimChaouch',
                        url : 'https://github.com/souhail642/devski.git';
                }
            }
               stage('Maven Version') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'mvn -version'
            }
        }
        stage('JDK Version') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'java -version'

        }}
     stage('MVN Clean Compile') {
            steps {

                // This step cleans and compiles the Maven project
                sh 'mvn clean compile'

        }}
         stage('Unit Testing using Mockito & JUnit') {
                    steps {

                            sh 'mvn test'

                }}

           stage('JaCoCo Coverage') {
            steps {
                script {

                    // No need to use 'dir' if the pom.xml is in the root directory
                    sh 'mvn jacoco:report -DskipTests'

            }
        }}

        stage('SonarQube analysis') {
                 steps {
                withSonarQubeEnv('sq1') {
                    sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.20:9000/ -Dsonar.login=squ_2c1c170f1409828b13f9c03df992ed9cc16782d3"



         }
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




         stage('docker create image'){
            steps{
                sh 'docker build -t ski .'
            }

          }


           stage('docker push'){
            steps{
                script{
                            sh 'docker login -u rimchaouch -p 191JFT3672R'

                        sh 'docker tag ski rimchaouch/gestion-station-ski'
                        sh 'docker push rimchaouch/gestion-station-ski'
                }

            }
           }
                 stage('docker compose'){
            steps {
             sh "docker-compose  up -d"

            }
            }
             stage('start prometheus/grafana'){
                        steps {
                         sh "docker start prometheus "
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
                             to: 'rimaachaouch@gmail.com',
                             mimeType: 'text/html'
               }
               success {
                   // Envoyer un e-mail en cas de réussite de la construction
                   emailext subject: "Build successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                             body: """<p>Build ${env.JOB_NAME} [${env.BUILD_NUMBER}] was successful.</p>
                                      <p>See the console output for more information:<a href='http://192.168.1.20:8080/job/devski/${env.BUILD_NUMBER}/console'>${env.BUILD_URL}console</a></p>""",
                             to: 'rimaachaouch@gmail.com',
                             mimeType: 'text/html'
               }
           }





}