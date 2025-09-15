def call(Map params = [:]) {
    dir("${env.WORKSPACE}") {
        def version   = params.version ?: "1.0.0"
        def envName   = params.envName ?: "staging"

        echo "Version: ${version}"
        echo "Environment: ${envName}"
        
        echo 'Starting containers...'
            sh 'docker-compose up -d'
    }
}
