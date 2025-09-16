// def call(String branch, String repoUrl, Map buildParams = [:]) {
//     return [
//         [name: 'Checkout', action: { checkoutrepo(branch, repoUrl) }],
//         [name: 'Build', action: { build() }],
//         [name: 'Deploy', action: { deploy() }]
//     ]
// }


def allStage(PipelineConfig config) {
    return [
        [
            name: 'Checkout', 
            action: { -> 
                checkoutrepo(config.branch, config.repoUrl) 
            }
        ],
        [
            name: 'Build', 
            action: { -> 
                build(config) // you can use config parameters inside build
            }
        ],
        [
            name: 'Deploy', 
            action: { -> 
                if(config.deploy) { // only deploy if deploy=true
                    deploy(config)
                } else {
                    echo "Skipping deploy"
                }
            }
        ]
        // Add more stages here if needed, all can use config parameters
    ]
}
