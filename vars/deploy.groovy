def call(Map config) {
    dir("${env.WORKSPACE}") {
        echo 'Starting containers...'
            sh 'docker-compose up -d'
    }
}
 