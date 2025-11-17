QCurios
============
QBaubles updated for 1.21.1 using neoforge.

Features:
- God Ring: Only acquirable through commands, gives total invulnerability to damage.
- Basic Ring: Has no effect, may later be relevant to ring acquisition/customization
- Stealth Ring: When equipped, grants instant, silent invisibility when crouching.


 How to add an new curio:
 1. Create a class in items that extends the desired type
 2. Add item here to a deferred register
 3. Give it an associate slot in resources/data/curios/tags/item/<slottype>.json
 4. Make sure the slot is on players in resources/data/qcurios/curios/entities/entities.json
 5. Make sure the slot has the right details (number of slots, for example) in resources/data/qcurios/curios/slots/ring.json
 6. Give it a texture in resources/assets/qcurios/textures/item/
 7. Give it localized english name in resources/assets/qcurios/lang/en_us.json
 

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Built with Neoforged:
==========
MDK Repo: https://github.com/neoforged/MDK
Community Documentation: https://docs.neoforged.net/