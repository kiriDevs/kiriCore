# kiriCore
![Maven Build](https://github.com/kiriDevs/kiriCore/workflows/Maven%20Build/badge.svg)
![CodeQL](https://github.com/kiriDevs/kiriCore/workflows/CodeQL/badge.svg)
[![CodeFactor](https://www.codefactor.io/repository/github/kiridevs/kiricore/badge)](https://www.codefactor.io/repository/github/kiridevs/kiricore)
![Deploy on GH Release](https://github.com/kiriDevs/kiriCore/workflows/Deploy%20on%20GH%20Release/badge.svg)
<br>
[Current version: 0.7.1]

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


### Code coverage
Since it is rather hard to write tests for a Minecraft-Server plugin outside of the environment of a Minecraft server, only the library part (API) is being unittested during the build process. But also that isn't possible 100%. Therefore, the full code coverage numbers do not (and probably will never) look really good. However, I've decided to calculate and publish them anyways, since these numbers are always interesting for these APIs.

With that said:
Code coverage statistics can be found [here](https://codecov.io/gh/kiriDevs/kiriCore)!
