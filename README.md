# __**KubeJS KeyBinds**__

Expands upon KubeJS by allowing you to modify existing KeyBinds and Categories

*KubeJS already offers custom KeyBinds, so this will not be added by this project.*

*WIKI is in progress*

***

Example client_scripts > keybinds.js

```
KeyBindExtendedEvents.modification(event => {
    // List all existing KeyBinds
    KeyBind.getKeyMappingNames().forEach(keyMapping => {
        console.log('KeyMapping: ' + keyMapping);
    });

    // List all existing Categories
    KeyBind.getKeyMappingCategories().forEach(category => {
        console.log('Category: ' + category);
    });

    // List all acceptable Keys
    KeyBind.getKeyNames().forEach(key => {
        console.log('Key: ' + key);
    });

    // List all acceptable Key Modifiers
    KeyBind.getKeyModifiers().forEach(modifier => {
        console.log('Modifier: ' + modifier);
    });

    // Remove the KeyBind from the game
    // void remove(string keyName);
    event.remove('key.saveToolbarActivator');
    event.remove('key.loadToolbarActivator');

    // Set the default KeyBind
    // void setDefaultKey(string keyName, string key);
    event.setDefaultKey('key.socialInteractions', 'KEY_UNKNOWN');

    // Set the default KeyBind with a Modifier
    // void setDefaultKey(string keyName, string key, string modifier);
    event.setDefaultKey('key.advancements', 'KEY_L', 'CONTROL');

    // Move the KeyBind to a new category
    // You can combine custom category names with a Language File (e.g. en_us.json > "key.categories.debug": "Debug")
    // void setCategory(string keyName, string category);
    event.setCategory('key.spectatorOutlines', 'key.categories.debug');
});

KeyBindExtendedEvents.categories(event => {
    // List current Category Order
    // By Default the only categories to order are the Vanilla Minecraft Categories
    var categories = event.getCategoriesInOrder();

    for(var i = 0; i < categories.size(); i++) {
        console.log('Index: ' + i + '; Category: ' + categories.get(i));
    }

    // Set our custom Debug category to appear right after the Miscellaneous category
    // void setDisplayIndex(string category, int index);
    event.setDisplayIndex('key.categories.debug', 7);
});
```

 

***

Copyright © 2026 [SpokenWig620933]
