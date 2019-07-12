# Ubuntu中docker安装

##[官方安装参考链接](https://docs.docker.com/install/linux/docker-ce/ubuntu/)



## 第一步：

```shell
sudo apt-get update
```



## 第二步：安装所需要包

```shell

sudo apt-get install \
     apt-transport-https \
     ca-certificates \
     curl \
     gnupg-agent \
     software-properties-common
```

安装过程报错

```text

E: Could not get lock /var/lib/dpkg/lock - open (11: Resource temporarily unavailable)
E: Unable to lock the administration directory (/var/lib/dpkg/), is another process using it?
```

解决[参考链接](https://blog.csdn.net/always_and_forever_/article/details/81001260)

```text
执行一下命定:
sudo rm /var/lib/apt/lists/lock 
sudo rm /var/cache/apt/archives/lock 
sudo rm /var/lib/dpkg/lock 
```

重新执行第二步安装命定

## 第三步增加docker官方GPG key



```she
 curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```

验证你现在有指纹证书key 9DC8 5822 9FC7 DD38 854A E2D8 8D81 803C 0EBF CD88，只需要搜索最后8个字符

```shell
sudo apt-key fingerprint 0EBFCD88

pub   rsa4096 2017-02-22 [SCEA]
      9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88
uid           [ unknown] Docker Release (CE deb) <docker@docker.com>
sub   rsa4096 2017-02-22 [S]
```

## 第四步设置docker稳定版仓库地址

```shell
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
```



## 第五步 安装docker

```shell

 sudo apt-get update

 sudo apt-get install docker-ce docker-ce-cli containerd.io
 
 
```



安装成功之后执行hello-world验证结果

```shell
sudo docker run hello-world
```



