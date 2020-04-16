# multi-module
Multi module maven project where each module can be a web app or main program or some piece of code that is runnable.
Portal is the main module/subproject that manages other ones.
All module starts from getting start of Spring


## docker
docker run -p 8090:8090 -p 8091:8091 -t 1248163264128/multi-module-with-springboot

docker run -ti --entrypoint /bin/sh 1248163264128/multi-module-with-springboot
