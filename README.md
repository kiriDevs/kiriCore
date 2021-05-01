# kiriCore
![Maven Build](https://github.com/kiriDevs/kiriCore/workflows/Maven%20Build/badge.svg)
![CodeQL](https://github.com/kiriDevs/kiriCore/workflows/CodeQL/badge.svg)
[![CodeFactor](https://www.codefactor.io/repository/github/kirimc-plugins/kiricore/badge)](https://www.codefactor.io/repository/github/kiridevs/kiricore)
![Deploy on GH Release](https://github.com/kiriDevs/kiriCore/workflows/Deploy%20on%20GH%20Release/badge.svg)
<br>
[Current version: 0.7.1]

---

**IMPORTANT:** Please note that this repository was moved from `kiriDevs/kiriCore` to `KiriMC-Plugins/kiriCore`.
For now however, the old URL will still work due to GitHub's redirect.
However, I can not guarantte that it will stay like that.
I do not currently plan to change any other metadata though, so you shouldn't need to update your project's `pom.xml` (except if you included the VCS URL).

---

1. [Commands](#commands)
2. [Code coverage](#code-coverage)

---

### Commands:
- /afk
  - Toggle your own AFK status!
  - permission: kiri.core.afk
- /isafk \<player name>
  - Check if a specific player is marked as AFK at the moment!
  - permission: kiri.core.afk.check
- /afklist
  - Print a list of all players marked as AFK at the moment
  - permission: kiri.core.afk.list
- /rename \<new item name>
  - Rename your currently held item! (max. 32 characters of length)
  - permission: kiri.core.rename
