def call(Map config) {
    dir("${env.WORKSPACE}") {
        def version   = config.version ?: "1.0.0"
        def envName   = config.envName ?: "staging"

        echo "Version: ${version}"
        echo "Environment: ${envName}"
        
        echo 'Starting containers...'
            sh 'docker-compose up -d'
    }
}
 