# Install stuff on OSX + Configurations
Things I need to setup on a new machine.

## .bash_profile OR .bashrc
* [https://joshstaiger.org/archives/2005/07/bash_profile_vs.html]()
* [https://scriptingosx.com/2017/04/about-bash_profile-and-bashrc-on-macos/]()

> OSX now uses the default shell as zsh. To migrate the scripts from bash to zsh. 

Read this for info
> [https://carlosroso.com/the-right-way-to-migrate-your-bash-profile-to-zsh/]() 

## Homebrew
````
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
````

## Java OpenJdk8
````
brew tap adoptopenjdk/openjdk
brew install --cask adoptopenjdk8
java -version
````

## Docker
Can be done from the following:
> [https://hub.docker.com/editions/community/docker-ce-desktop-mac/]()

## MySql
Version 8 can be installed from the oracle's website. To install 5.7:
````
brew install mysql@5.7
brew tap homebrew/services
brew services start mysql@5.7
brew services list
brew link mysql@5.7 --force
````

## IntelliJ Idea ignore vcs files pesty notification solution?
> [https://www.jetbrains.com/help/idea/enabling-version-control.html#associate_directory_with_VCS]()

````
Also add your local run configuration profile in .git/info/exclude
```` 

The advantage of `.gitignore` is that it can be checked into the repository itself, unlike 
`.git/info/exclude`. Another advantage is that you can have multiple `.gitignore` files, 
one inside each directory/subdirectory for directory specific ignore rules, unlike 
`.git/info/exclude`. So, `.gitignore` is available across all clones of the repository. 
Therefore, in large teams all people are ignoring the same kind of files Example `*.db, *.log` 
and you can have more specific ignore rules because of multiple`.gitignore` 
`.git/info/exclude` is available for individual clones only, hence what one person 
ignores in his clone is not available in some other person's clone. For example, if someone 
uses Eclipse for development it may make sense for that developer to add `.build` folder to 
`.git/info/exclude` because other devs may not be using Eclipse. In general, files/ignore rules 
that have to be universally ignored should go in `.gitignore`, and otherwise files that you 
want to ignore only on your local clone should go into `.git/info/exclude`

## Redis
````
brew install redis
brew servies start redis
redis-cli ping
````

## RabbitMq
````
brew install rabbitmq
vim ~/.zshrc
````

Update the env var:
````
export HOMEBREW_RABBITMQ=/usr/local/Cellar/rabbitmq/3.7.11/sbin/
export PATH=$PATH:$HOMEBREW_RABBITMQ
````

> To start the server: `rabbitmq-server` or `brew serrvices start rabbitmq`
> To stop the server: `brew serrvices stop rabbitmq`

To be noted that RabbitMQ server and CLI tools are inside the directory:

````
/usr/local/Cellar/rabbitmq/{version}/sbin/
```` 

And we can access the RabbitMQ dashboard from http://localhost:15672 ,
the default username and password is guest and guest respectively.

To stop RabbitMQ press `Ctrl + C`

## Install maven
> [https://maven.apache.org/install.html]()

## Generating an SSH key
An SSH key consists of a pair of files. One is the private key, which should never be shared with anyone. The other is the public key. The other file is a public key which allows you to log into the servers. When you generate the keys, you will use ssh-keygen to store the keys in a safe location so you can bypass the login prompt when connecting to your instances.

To generate SSH keys:

````
ssh-keygen -t rsa
````

This starts the key generation process. When you execute this command, the ssh-keygen utility prompts you to indicate where to store the key.

Press the ````ENTER```` key to accept the default location. The ssh-keygen utility prompts you for a passphrase.

Type in a passphrase. You can also hit the ````ENTER```` key to accept the default (no passphrase). However, this is not recommended.
Warning! You will need to enter the passphrase a second time to continue.

After you confirm the passphrase, the system generates the key pair.

Private key location: ````~/.ssh/id_rsa````

Public key location: ````~/.ssh/id_rsa.pub````

To copy pub key to clipboard: ````pbcopy < ~/.ssh/id_rsa.pub````