# Install stuff on OSX

### .bash_profile OR .bashrc
https://joshstaiger.org/archives/2005/07/bash_profile_vs.html

https://scriptingosx.com/2017/04/about-bash_profile-and-bashrc-on-macos/

BUT, OSX now uses the default shell as zsh. To migrate the scripts from bash to zsh. Read this for info:

https://carlosroso.com/the-right-way-to-migrate-your-bash-profile-to-zsh/ 

----
### Homebrew
`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`

----
### Java OpenJdk8
`brew tap adoptopenjdk/openjdk`

`brew install --cask adoptopenjdk8`

`java -version`

----
### Docker
Can be done from the following:
https://hub.docker.com/editions/community/docker-ce-desktop-mac/

----
### MySql
Version 8 can be installed from the oracle's website. To install 5.7:

`brew install mysql@5.7`

`brew tap homebrew/services`

`brew services start mysql@5.7`

`brew services list`

`brew link mysql@5.7 --force`

----
### IntelliJ Idea ignore vcs files pesty notification solution?
https://www.jetbrains.com/help/idea/enabling-version-control.html#associate_directory_with_VCS

----
### Redis
`brew install redis`

`brew servies start redis`

`redis-cli ping`

----
### RabbitMq
`brew install rabbitmq`

`vim ~/.zshrc`

Update the env var:
```
#HOMEBREW RABBITMQ
export HOMEBREW_RABBITMQ=/usr/local/Cellar/rabbitmq/3.7.11/sbin/
export PATH=$PATH:$HOMEBREW_RABBITMQ
```

To start the server: `rabbitmq-server`

To be noted that RabbitMQ server and CLI tools are inside the directory:

`/usr/local/Cellar/rabbitmq/{version}/sbin/` 

And we can access the RabbitMQ dashboard from http://localhost:15672 ,
the default username and password is guest and guest respectively.

To stop RabbitMQ press `Ctrl + C`