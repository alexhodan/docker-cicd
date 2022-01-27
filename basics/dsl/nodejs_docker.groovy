job('NodeJS Docker example') {
    scm {
        git('git://https://github.com/alexhodan/JB.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('alexhodan/dsl')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('docker_credentials')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

