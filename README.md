# Shootcraft
A basic Funcraft's ShootCraft reimplementation w more features

# Missing features
- maxGameDuration
- maxPlayerCount
- scoreboard
- game ending
- actually use TitleUtils
- NMS packets ViaVersion handling
- Multi version builds

# Building
As of now this is only buildable for 1.8.8 until I figure out how to make Gradle (🤡) work for multiple versions without reflections.

Since this uses NMS, you need to run buildtools 1.8.8 first (https://www.spigotmc.org/, "Downloads" tab) using this:

`java -jar BuildTools.jar --rev 1.8.8`