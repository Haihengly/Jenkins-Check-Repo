def call(Map config) {
    def CLONE_DIR = "${env.HOME}/code-clone"

    sh """
        rm -rf ${CLONE_DIR}
        cd ${env.HOME}
        git clone ${config.repoUrl} code-clone
        ls -l ${CLONE_DIR}
    """
}



    // git (
    //     branch: config.branch, 
    //     url: config.repoUrl, 
    //     credentialsId: 'git_token'
    // )
}
