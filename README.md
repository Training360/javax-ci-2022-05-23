# Java CI tanfolyam

## Labor 1.

* Könyvtár létrehozás: `javax-ci-2022-05-23`
* Visual Studio Code indítása a könyvtárban
* Lokális Git repo létrehozása: `git init`
* Git felhasználónév és e-mail cím beállítása:

```shell
git config --global user.name "FIRST_NAME LAST_NAME"
git config --global user.email "MY_NAME@example.com"
```

* Felhasználói felületen `git add` és `git commit`

## Labor 2.

```shell
git remote add origin https://github.com/Training360/javax-ci-2022-05-23.git
git push origin master
```

Alternatíva:

```shell
mkdir javax-ci-2022-05-23-oktato
cd javax-ci-2022-05-23-oktato
git clone https://github.com/Training360/javax-ci-2022-05-23.git .
git pull
```

## Labor 3. - Alkalmazás buildelése

```
set JAVA_HOME=c:\Program Files\Java\jdk-17.0.3.1\
mvnw package
```

