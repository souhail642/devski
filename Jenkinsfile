pipeline {

    agent any
    stages {
         stage ('Checkout GIT'){
                steps {
                    echo 'pulling... ';
                        git branch : 'souhail',
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
                    sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.13:9000/ -Dsonar.login=squ_91796cdee6f2c88f142b64f56ecaba505f64cc72"



         }
    }}
        stage('Unit Testing using Mockito & JUnit') {
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




         stage('docker create image'){
            steps{
                sh 'docker build -t ski .'
            }

          }


           stage('docker push'){
            steps{
                script{
                            sh 'docker login -u chernisouhail -p asmaasmaas'

                        sh 'docker tag ski chernisouhail/gestion-station-ski'
                        sh 'docker push chernisouhail/gestion-station-ski'
                }

            }
           }
                 stage('docker compose'){
            steps {
             sh "docker-compose  up -d"

            }
        }
        }
        }