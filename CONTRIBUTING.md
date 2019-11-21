Como configurar o projeto para desenvolvimento


**Configuração do servidor de arquivo MinIO**


**1.**  Primeiramente é necessário estar com o docker instalado em sua máquina, acesse o [link](https://docs.docker.com/install/linux/docker-ce/ubuntu/) para realizar a instalação do docker.

**2.**  Para poder usar o comando que irá executar o servidor é necessário configurar o docker para usuário que não seja root, isso possibilitará executar o comando sem a necessidade de usar `sudo`.

* `sudo groupadd docker`

* `sudo usermod -aG docker $USER`, obs: substituir `$USER` pelo seu usuário

* `newgrp docker`

Caso tenha dúvidas, acesse o seguinte [link](https://docs.docker.com/install/linux/linux-postinstall/).

**3.** Execute o seguinte comando:

*  `docker run -p 9000:9000 -d --name minio -e "MINIO_ACCESS_KEY=minioserver" -e "MINIO_SECRET_KEY=minioserver" -v ~/minio:/data minio/minio server /data`

Certifique-se que ao executar o comando, foi criado uma pasta **minio** no local da execução do comando. 

**4.** Demais comandos:

* `docker ps` (Irá mostrar informações sobre o container que foi criado no passo **3**)
* `docker stop minio` (Irá parar a execução do servidor)
* `docker start minio` (Irá retomar a execução do servidor)
* `docker rm -f minio` (Irá remover o container que foi criado no comando `run`

Obs: Caso tenha usado o comando `docker stop minio` não é necessário adicionar `-f` para remover o container)