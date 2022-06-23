# Mob Egg Dropper
A simple plugin that allows players to get the spawn eggs of the mobs they kill. Mobs that are able to drop eggs are configurable.


## Commands:
 - > /med [list] [remove/add]
   - Modify the config and show/change what mobs can drop eggs. *Requires permission: mobEggDropper.edit*



 - > [code]/med [reload]
    - Reloads the plugin. *Requires permission: mobEggDropper.edit*

 - > /egg-toggle [check]
    - Allows you to toggle whether or not you will get an egg after killing a mob. Adding 'check' to the end will show if egg drops are toggled. *(For all players)*


## Config:
Default config.yml looks like

    mobs:
    - SHEEP
    - CHICKEN
    - PIG  

To add or remove mobs you want to be able to drop eggs either change this config file or use the 
>/med [list] [remove/add]
>
Mob names are all capital letters and spelling/case is important.


## Other:
This is my first plugin uploaded to spigot. I've been exploring MC plugins for a while and wanted to make something I use on my personal server as well as something other people might find useful. If you encounter problems or errors please try to reach out to me on discord nerdYkiD_72#8281.
