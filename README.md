Faster Iris Shadow Mapping is a really lightweight mod that prevents unneeded things from rendering during the shadow pass.

**This project does not remplace Iris, both needs to be used at the same time for it to work**

# How it works
During the shadow pass, for opaque models, the color of the model won't have any visual change, a blue car and a red car both cast the same shadow, no matter their color.
So what this mod does is it prevents things like the text on signs, the banner patterns, the enchantment glint etc from rendering during the shadow pass.

# Performance showcase
**Small base in a flat world (26.1.2 - 1.0.0)**
<table>
  <caption>
    With / Without | 100FPS / 87FPS (avg) | 63FPS / 58 FPS (0.05% low)
  </caption>
  <tr>
    <td> <img alt = "With: 100FPS" src = "https://cdn.modrinth.com/data/cached_images/5b37bb3b56bd1587c24be025d4e2b31fc6adbf37.png">
    </td>
    <td> <img alt = "Without: 87FPS" src = "https://cdn.modrinth.com/data/cached_images/59940b57658ea0553c9cba19a7e0ea9d143a1865.png">
    </td>
  <tr>
</table>

- +10% FPS

**A lot of signs and banners (26.1.2 - 1.0.0)**
<table> 
  <caption>
    With / Without | 15FPS / 9FPS (avg) | 10FPS / 1 FPS (0.05% low)
  </caption>
  <tr>
    <td> <img alt = "With: 15FPS" src = "https://cdn.modrinth.com/data/cached_images/37aa042ff722457b62dd76c338ce541387c6a2ef.png">
    </td>
    <td> <img alt = "Without: 9FP" src = "https://cdn.modrinth.com/data/cached_images/7d0d9a7fba8133e530437a6088fccc3db7463055.png">
    </td>
  <tr>
</table>

- +50% FPS on average
- +1000% FPS on 0.05% low

# Modpacks

Feel free to use this mod in your modpacks without asking for permission.