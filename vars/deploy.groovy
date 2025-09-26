def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service"
    def envFile = config.envName == "prod" ? ".env.prod" : ".env.dev"
    def composeFiles = "-f docker-compose.base.yml -f docker-compose.${config.envName}.yml"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}
        docker compose ${composeFiles} --env-file ${envFile} down
        docker compose ${composeFiles} --env-file ${envFile} up -d
        
    """

}
 