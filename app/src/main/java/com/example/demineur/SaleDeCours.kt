// On revient ici
// On va faire une leçon sur les appels de fonctions

fun mickey(donald: Int): Int = 4
fun donald(): Int = 5

// Si j'écris ça, est-ce que tu comprends ?
mickey(donald())
// On appelle la fonction mickey qui appelle elle meme la fonction donald ? précise.
// C'est pas mickey qui appelle donald
// Si mickey appelait donald on aurait ça :

// Ici oui, mickey2 appelle donald
fun mickey2(): Int = donald()

// On appelle mickey avec la fonction donald en paramètre alors ?
// On appelle mickey avec le resultat de la fonction donald en paramètre --> Oui je vois
// Si on appelait mickey avec la fonction donald en paramètre on aurait ça :
fun mickey2(d: () -> Int): Int = d()
fun main() {
// Ouais ok nan là on fait un triple saut dans l'inconnu pour toi xD ---> orf j'ai vu que t'as abrévié le paramètres j'ai perdu le fil, combo "Unit" + "->"
// Mais là en l'occurence, on a bien "mickey2 appelle donald"--> je vois
// Enfin. Concrètement si tu regardes la fonction "mickey2", tu vois qu'on utilise pas le paramètre
// Donc là en vrai, on appelle pas donald
// Là on l'appelle --> mon cerveau n'a pas tout assimilé à la ligne en dessous
// T'as pas besoin de ça pour l'instant. Ca s'appelle une method reference.
    mickey2(::donald)
}

// Euh je pense
// Est-ce que tu comprends que donald est exécutée _avant_ mickey ?
// Mets toi à la place de l'exécuteur du programme
/// Tu arrives sur cette ligne
// Tu vois un appel à mickey
// Mais pour exécuter mickey, t'as besoin d'un Int
// Tu regardes le paramètre, tu vois que c'est un appel de fonction
// Du coup t'as pas de Int, t'as "un appel de fonction"
// Faut donc que tu exécutes la fonction pour récupérer le Int, et ensuite exécuter mickey
// Oui je vois  (je laisse les commentaires pour que t'y reviennes si besoin)
// Et ça, c'est la façon dont un programme s'exécute
// Sur N niveaux

fun mickey2(donald: Int): Int = 4
fun donald(pluto: Int): Int = 5
fun pluto(minnie: Int): Int = 5
fun minnie(): Int = 5


mickey2(donald(pluto(minnie()))
//il execute minnie puis pluto puis donald puir mickey2
// Oui, si tu analyses comme je l'ai fait en haut, t'es obligé
// Parce que pour mickey2 faut la valeur renvoyée par donald
// Pour donald, faut la valeur renvoyée par pluto, etc
// Et ainsi de suite


// bonsoir
fun mickey4(d: () -> Int): Int = 4

// Ici,j'ai une fonction en paramètre. Il s'avère que c'est le dernier paramètre de mickey4
// J'ai donc accès à une nouvelle syntaxe
// Je peux IMPLEMENTER UNE FONCTION ANONYME A LA VOLEE (= une lambda expression)

// Ici, le paramètre d c'est ce que je surligne -- un paramètre entre accolades ?!
// C'est le syntaxe des lambdas expressions. Sinon je devrais faire ça :
// C'est pas pratique à lire avec les parenthèses. Donc le langage autorise qu'on s'en passe pour rendre le tout plus lisible - pourquoi mickey4(12) suffit pas ?
// C'est quoi le paramètre de mickey4 ? -- d : Int -- alors non. xD -- d avec un retour de type Int ?
// C'est une fonction qui n'a pas de paramètre et renvoie Int -- Ok , la flèche elle m'arrache le cerveau à chaque fois
// Y'a rien de spécial sur cette flèche, c'est juste un symbole comme un autre pour indiquer "une fonction" -- je note ça
// Si tu écris ça : mickey4(12), tu envoies quoi à mickey4 ? un Int = 12
// Ok. Est que :
// - Un Int
// - Une fonction sans paramètre qui renvoie un Int
// C'est la même chose ?
// Non parce que un Int il est "figé" ? alors que la fonction bon bah elle a des variables sans doute ?
// https://media.tenor.com/HM-tHjTAB-sAAAAC/sad-cellphone.gif -- xdddd
// Là du coup faut que tu raisonnes en mode "objet"
// Si je te demande si un chien et une voiture c'est la même chose, tu me diras non sans hésiter
// Et bah là c'est la même chose
// C'est pas la même chose parce que c'est pas les mêmes types. - tout simplement
// Un chien ça court, une voiture ça roule
// Un Int ça s'additionne, une fonction ça s'appelle
// C'est deux choses qui n'ont pas les mêmes comportements -- ok. Tu peux te raccrocher à ce qu'on disait l'autre jour
// Même si dans les faits, c'est "faux", ça peut peut-être aider :
// Un chien c'est "courrable", une voiture c'est "roulable"
// Un Int c'est "additionnable", une fonction c'est "appelable" -- je comprend
// Et surtout que, ça ne modélise pas la même chose, pas le même concept.
// Si je te dis "un chiffre" vs "une structure informatique complexe", bah c'est un Int vs une fonction -- Ok
// Donc quand une fonction attend un "concept" précis (un _type_ concrètement)
// Tu ne peux pas envoyer autre chose que le type en question - oui
// Donc si ma fonction attend une fonction, j'ai pas le droit de lui envoyer un Int - je comprend
// Et la syntaxe pour créer une fonction basique de type () -> Unit (aucun paramètre, renvoie """rien"""), c'est ça :
val f = {
}
// Là, avec l'inférence, f est de type () -> Unit. Donc je peux l'utiliser partout où ça attend un objet de type () -> Unit -- je vois
// Pour changer le Unit, je dois renvoyer quelque chose
val f2 = { 12 }

// Ici je laisse faire l'inférence. Je renvoie 12. Par défaut, l'inférence dit que 12 c'est un Int. Donc f2 est de type () -> Int -- je vois
// Mais je peux forcer un retour
val f3 = { 12.toFloat() }

// Je force mon 12 en Float (= nombre décimal). Donc f3 est de type () -> Float --  et ça renvoie quoi concrètement en float 12 ? 12.0000000 --
// aaaanh je pensais c'était entre 0 et 1 mais ok, je suis juste un peu débile quoi
// Nan ça se contente juste de "transformer" mon 12 entier en 12 décimal -- OK
// Donc là pour l'instant j'ai joué qu'avec le retour, maintenant je veux ajouter des paramètres à ma fonction
// Le syntaxe c'est ça :
 val f4 = { x -> 12.toFloat() }

 // Pourquoi est-ce que c'est rouge selon toi ?

val f5 = { x : Int -> 12.toFloat() }

// Trés bien. Du coup f5 c'est quoi son type ? -- Un Int qui est forcé en Float
// https://ih0.redbubble.net/image.903945693.3701/raf,360x360,075,t,fafafa:ca443f4786.jpg -- j'ai couiné de rire -- parfait
// Un Int qui renvoie un Int forcé en Float ---> ce n'est pas un type de toute façon
// N'oublie pas que depuis tout à l'heure, je ne fais que créer des fonctions. Donc quand je dis "c'est quoi le type de f5 ? -- ah, c'est une fonction le type ( j'attend le gif )
// https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSALuDhXxF5nidxQSAeVM5h12ll6VVfzT32xqEIGjeMfQ&s -- xD jpp
// ah, c'est une fonction le type --> cette phrase ne va pas. Enfin. Disons que c'est possible que t'aies compris, mais si oui c'est très mal formulé
// Une fonction c'est une "structure informatique"
// En Kotlin, on peut définir une variable qui est une fonction.
// Le type de cette variable c'est donc "une fonction". Mais pour définir une fonction 100%, on a besoin d'informations supplémentaires :
// - Ses paramètres
// - Son type de retour -- Oui et le contenu de son retour inchallah
// https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHMr-rfiaTxgewJHboboZOwOPkOwkqaaTkYssqlma2Ag&s -- ^^ j'ai senti en tapant que c'était pas gagné
// le contenu de son retour --> c'est très mal formulé mais je vois ce que tu veux dire -- moi aussi je vois ce que je veux dire
// Je reprends ma phrase en changeant un truc :
// Mais pour déclarer un paramètre de type "fonction" à 100%, on a besoin d'informations supplémentaires :
// - Ses paramètres
// - Son type de retour
// (parfois je fais des raccourcis sur les termes que j'utilise mais mon utilisation de "définir" t'a induit en erreur) -- ya moyen

// Donc, c'est quoi le type de f5 ?
// Ca a quelle forme un type "fonction" ? () -> Unit c'est ça ? Oui.
// Tu as écrit quel type de fonction là ? une fonction anonyme ? Non -- ^^
// Tu as écrit une fonction sans paramètre qui retourne """"rien"""" (oui parce que Nothing ça existe en Kotlin et c'est pas pareil que Unit) --
// Est-ce que Unit c'est quelque chose vide et Nothing c'est l'absence de retour ?
 // Oui. Nothing c'est "la fonction ne retournera jamais". -- https://tenor.com/view/gentleman-giga-chad-sigma-male-gif-25702945 -- gg
 // Donc, c'est quoi le type de f5 ? Analyse la fonction et dis moi les choses que tu constates
 // On a besoin de quoi pour définir un type "fonction" ?
// C'est une fonction qui a pour paramètre x de type Int et qui retourne 12 qui est forcé en Float donc 12.00000 ? Oui c'est bien -- big brain
// Donc c'est quoi son type ? x -> 12.00000. Tu confonds type et "valeurs" -- Int -> Float ? Oui, il manque juste les parenthèses : (Int) -> Float -- OK
// Quand on définit le type d'une fonction, on s'en fout des valeurs qu'on aura, ce qui nous intéresse c'est les types -- ok


// Je déclare une fonction, allez on va changer de domaine :
fun ashe() : Int

// Je définis une fonction :
fun garen() : Int { return 4 }

// Tu comprends la diff ?
// Cette partie là : { return 4 } -- On appelle ça L'IMPLEMENTATION d'une fonction -- https://tenor.com/view/calculating-puzzled-math-confused-confused-look-gif-14677181
// Ce terme implementation je vais beaucoup l'employer

fun main2() {
    mickey4({12})
    // ICI ----> {12}  ---> C'EST L'IMPLEMENTATION DE LA FONCTION ENVOYEE EN PARAMETRE A MICKEY4
    // Il s'avère que cette fonction n'a pas de nom, comme ashe ou garen. C'est donc une fonction anonyme. -- je pense avoir compris
}