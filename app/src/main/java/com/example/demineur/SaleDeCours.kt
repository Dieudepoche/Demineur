// On revient ici
// On va faire une le�on sur les appels de fonctions

fun mickey(donald: Int): Int = 4
fun donald(): Int = 5

// Si j'�cris �a, est-ce que tu comprends ?
mickey(donald())
// On appelle la fonction mickey qui appelle elle meme la fonction donald ? pr�cise.
// C'est pas mickey qui appelle donald
// Si mickey appelait donald on aurait �a :

// Ici oui, mickey2 appelle donald
fun mickey2(): Int = donald()

// On appelle mickey avec la fonction donald en param�tre alors ?
// On appelle mickey avec le resultat de la fonction donald en param�tre --> Oui je vois
// Si on appelait mickey avec la fonction donald en param�tre on aurait �a :
fun mickey2(d: () -> Int): Int = d()
fun main() {
// Ouais ok nan l� on fait un triple saut dans l'inconnu pour toi xD ---> orf j'ai vu que t'as abr�vi� le param�tres j'ai perdu le fil, combo "Unit" + "->"
// Mais l� en l'occurence, on a bien "mickey2 appelle donald"--> je vois
// Enfin. Concr�tement si tu regardes la fonction "mickey2", tu vois qu'on utilise pas le param�tre
// Donc l� en vrai, on appelle pas donald
// L� on l'appelle --> mon cerveau n'a pas tout assimil� � la ligne en dessous
// T'as pas besoin de �a pour l'instant. Ca s'appelle une method reference.
    mickey2(::donald)
}

// Euh je pense
// Est-ce que tu comprends que donald est ex�cut�e _avant_ mickey ?
// Mets toi � la place de l'ex�cuteur du programme
/// Tu arrives sur cette ligne
// Tu vois un appel � mickey
// Mais pour ex�cuter mickey, t'as besoin d'un Int
// Tu regardes le param�tre, tu vois que c'est un appel de fonction
// Du coup t'as pas de Int, t'as "un appel de fonction"
// Faut donc que tu ex�cutes la fonction pour r�cup�rer le Int, et ensuite ex�cuter mickey
// Oui je vois  (je laisse les commentaires pour que t'y reviennes si besoin)
// Et �a, c'est la fa�on dont un programme s'ex�cute
// Sur N niveaux

fun mickey2(donald: Int): Int = 4
fun donald(pluto: Int): Int = 5
fun pluto(minnie: Int): Int = 5
fun minnie(): Int = 5


mickey2(donald(pluto(minnie()))
//il execute minnie puis pluto puis donald puir mickey2
// Oui, si tu analyses comme je l'ai fait en haut, t'es oblig�
// Parce que pour mickey2 faut la valeur renvoy�e par donald
// Pour donald, faut la valeur renvoy�e par pluto, etc
// Et ainsi de suite


// bonsoir
fun mickey4(d: () -> Int): Int = 4

// Ici,j'ai une fonction en param�tre. Il s'av�re que c'est le dernier param�tre de mickey4
// J'ai donc acc�s � une nouvelle syntaxe
// Je peux IMPLEMENTER UNE FONCTION ANONYME A LA VOLEE (= une lambda expression)

// Ici, le param�tre d c'est ce que je surligne -- un param�tre entre accolades ?!
// C'est le syntaxe des lambdas expressions. Sinon je devrais faire �a :
// C'est pas pratique � lire avec les parenth�ses. Donc le langage autorise qu'on s'en passe pour rendre le tout plus lisible - pourquoi mickey4(12) suffit pas ?
// C'est quoi le param�tre de mickey4 ? -- d : Int -- alors non. xD -- d avec un retour de type Int ?
// C'est une fonction qui n'a pas de param�tre et renvoie Int -- Ok , la fl�che elle m'arrache le cerveau � chaque fois
// Y'a rien de sp�cial sur cette fl�che, c'est juste un symbole comme un autre pour indiquer "une fonction" -- je note �a
// Si tu �cris �a : mickey4(12), tu envoies quoi � mickey4 ? un Int = 12
// Ok. Est que :
// - Un Int
// - Une fonction sans param�tre qui renvoie un Int
// C'est la m�me chose ?
// Non parce que un Int il est "fig�" ? alors que la fonction bon bah elle a des variables sans doute ?
// https://media.tenor.com/HM-tHjTAB-sAAAAC/sad-cellphone.gif -- xdddd
// L� du coup faut que tu raisonnes en mode "objet"
// Si je te demande si un chien et une voiture c'est la m�me chose, tu me diras non sans h�siter
// Et bah l� c'est la m�me chose
// C'est pas la m�me chose parce que c'est pas les m�mes types. - tout simplement
// Un chien �a court, une voiture �a roule
// Un Int �a s'additionne, une fonction �a s'appelle
// C'est deux choses qui n'ont pas les m�mes comportements -- ok. Tu peux te raccrocher � ce qu'on disait l'autre jour
// M�me si dans les faits, c'est "faux", �a peut peut-�tre aider :
// Un chien c'est "courrable", une voiture c'est "roulable"
// Un Int c'est "additionnable", une fonction c'est "appelable" -- je comprend
// Et surtout que, �a ne mod�lise pas la m�me chose, pas le m�me concept.
// Si je te dis "un chiffre" vs "une structure informatique complexe", bah c'est un Int vs une fonction -- Ok
// Donc quand une fonction attend un "concept" pr�cis (un _type_ concr�tement)
// Tu ne peux pas envoyer autre chose que le type en question - oui
// Donc si ma fonction attend une fonction, j'ai pas le droit de lui envoyer un Int - je comprend
// Et la syntaxe pour cr�er une fonction basique de type () -> Unit (aucun param�tre, renvoie """rien"""), c'est �a :
val f = {
}
// L�, avec l'inf�rence, f est de type () -> Unit. Donc je peux l'utiliser partout o� �a attend un objet de type () -> Unit -- je vois
// Pour changer le Unit, je dois renvoyer quelque chose
val f2 = { 12 }

// Ici je laisse faire l'inf�rence. Je renvoie 12. Par d�faut, l'inf�rence dit que 12 c'est un Int. Donc f2 est de type () -> Int -- je vois
// Mais je peux forcer un retour
val f3 = { 12.toFloat() }

// Je force mon 12 en Float (= nombre d�cimal). Donc f3 est de type () -> Float --  et �a renvoie quoi concr�tement en float 12 ? 12.0000000 --
// aaaanh je pensais c'�tait entre 0 et 1 mais ok, je suis juste un peu d�bile quoi
// Nan �a se contente juste de "transformer" mon 12 entier en 12 d�cimal -- OK
// Donc l� pour l'instant j'ai jou� qu'avec le retour, maintenant je veux ajouter des param�tres � ma fonction
// Le syntaxe c'est �a :
 val f4 = { x -> 12.toFloat() }

 // Pourquoi est-ce que c'est rouge selon toi ?

val f5 = { x : Int -> 12.toFloat() }

// Tr�s bien. Du coup f5 c'est quoi son type ? -- Un Int qui est forc� en Float
// https://ih0.redbubble.net/image.903945693.3701/raf,360x360,075,t,fafafa:ca443f4786.jpg -- j'ai couin� de rire -- parfait
// Un Int qui renvoie un Int forc� en Float ---> ce n'est pas un type de toute fa�on
// N'oublie pas que depuis tout � l'heure, je ne fais que cr�er des fonctions. Donc quand je dis "c'est quoi le type de f5 ? -- ah, c'est une fonction le type ( j'attend le gif )
// https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSALuDhXxF5nidxQSAeVM5h12ll6VVfzT32xqEIGjeMfQ&s -- xD jpp
// ah, c'est une fonction le type --> cette phrase ne va pas. Enfin. Disons que c'est possible que t'aies compris, mais si oui c'est tr�s mal formul�
// Une fonction c'est une "structure informatique"
// En Kotlin, on peut d�finir une variable qui est une fonction.
// Le type de cette variable c'est donc "une fonction". Mais pour d�finir une fonction 100%, on a besoin d'informations suppl�mentaires :
// - Ses param�tres
// - Son type de retour -- Oui et le contenu de son retour inchallah
// https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHMr-rfiaTxgewJHboboZOwOPkOwkqaaTkYssqlma2Ag&s -- ^^ j'ai senti en tapant que c'�tait pas gagn�
// le contenu de son retour --> c'est tr�s mal formul� mais je vois ce que tu veux dire -- moi aussi je vois ce que je veux dire
// Je reprends ma phrase en changeant un truc :
// Mais pour d�clarer un param�tre de type "fonction" � 100%, on a besoin d'informations suppl�mentaires :
// - Ses param�tres
// - Son type de retour
// (parfois je fais des raccourcis sur les termes que j'utilise mais mon utilisation de "d�finir" t'a induit en erreur) -- ya moyen

// Donc, c'est quoi le type de f5 ?
// Ca a quelle forme un type "fonction" ? () -> Unit c'est �a ? Oui.
// Tu as �crit quel type de fonction l� ? une fonction anonyme ? Non -- ^^
// Tu as �crit une fonction sans param�tre qui retourne """"rien"""" (oui parce que Nothing �a existe en Kotlin et c'est pas pareil que Unit) --
// Est-ce que Unit c'est quelque chose vide et Nothing c'est l'absence de retour ?
 // Oui. Nothing c'est "la fonction ne retournera jamais". -- https://tenor.com/view/gentleman-giga-chad-sigma-male-gif-25702945 -- gg
 // Donc, c'est quoi le type de f5 ? Analyse la fonction et dis moi les choses que tu constates
 // On a besoin de quoi pour d�finir un type "fonction" ?
// C'est une fonction qui a pour param�tre x de type Int et qui retourne 12 qui est forc� en Float donc 12.00000 ? Oui c'est bien -- big brain
// Donc c'est quoi son type ? x -> 12.00000. Tu confonds type et "valeurs" -- Int -> Float ? Oui, il manque juste les parenth�ses : (Int) -> Float -- OK
// Quand on d�finit le type d'une fonction, on s'en fout des valeurs qu'on aura, ce qui nous int�resse c'est les types -- ok


// Je d�clare une fonction, allez on va changer de domaine :
fun ashe() : Int

// Je d�finis une fonction :
fun garen() : Int { return 4 }

// Tu comprends la diff ?
// Cette partie l� : { return 4 } -- On appelle �a L'IMPLEMENTATION d'une fonction -- https://tenor.com/view/calculating-puzzled-math-confused-confused-look-gif-14677181
// Ce terme implementation je vais beaucoup l'employer

fun main2() {
    mickey4({12})
    // ICI ----> {12}  ---> C'EST L'IMPLEMENTATION DE LA FONCTION ENVOYEE EN PARAMETRE A MICKEY4
    // Il s'av�re que cette fonction n'a pas de nom, comme ashe ou garen. C'est donc une fonction anonyme. -- je pense avoir compris
}