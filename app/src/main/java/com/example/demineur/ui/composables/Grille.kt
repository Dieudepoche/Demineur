package com.example.demineur.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.demineur.modeles.Case

private const val GRID_SIZE = 9
private const val BOMB_COUNT = 10

fun generateGrid(bombCount: Int): List<List<Case>> {
    val bombCoordinates = mutableSetOf<Pair<Int, Int>>()

    val bombCountMap = mutableMapOf<Pair<Int, Int>, Int>()

    while (bombCoordinates.size < BOMB_COUNT) {
        // Donc ici on génère des coordonnées aléatoires
        val coordonnees = (1..GRID_SIZE).random() to (1..GRID_SIZE).random()
        // -------> ICI ON AJOUTE LES COORDONNEES DANS NOTRE SET
        // -------> CA NE S'AJOUTE PAS SI CA EXISTE DEJA
        // -------> ON PEUT LE SAVOIR EN REGARDANT LE RETOUR
        // Donc on est partis, on enregistre le résultat

        // je chie sur le français dans le code
        // :(
        // Mais parce que t'es gentil, j'accepterai
        // :( ----> :)
        // Le pti salaud il a remis resultat
        // ^^, sur Github j'ai pas mis feature j'ai mis Fonctionnalités
        // Et main s'est transformé mystérieusement en "Principale"

        val resultat = bombCoordinates.add(coordonnees)

        // Tu fais quoi ici ?
        // On revient ici.
        // Sachant tout ça, tu fais quoi ici ?
        //je dis Si et jexecute bombCoordinates.add(coordonnees) dans mon si
        // --> Tu exécutes add et ensuite tu vérifies le retour (du plus imbriqué au moins imbriqué)
        // Tu exécutes add. C'est ça qu'est important.
        // C'est quoi la ligne juste avant ton if
        //Jexecute add
        // Et on a vu qu'il se passait quoi si j'exécutais add deux fois de suite ?
        // Le deuxieme renverra false
        // Oui. Donc ?
        //Il faut que je le fasse qu'une fois. Oui mais là je veux la réponse de ce qu'il se passe dans l'état actuel
        // Il renverra true, puis false. Donc ? xD
        // Ca sera toujours faux. Oui. Ton if ne marchera _jamais_
        // Mais du coup j'ai jsute à retirer la ligne du dessus ^^
        // Tu peux mais, on peut toujours tout faire de 14 façons.
        // Et celle-là elle rajoute une action implicite dans ton if
        // Il faut imaginer que t'es dans un projet, et que d'autres gens vont travailler avec toi
        // if(bombCoordinates.add(coordonnees)){}

        // Ok maintenant on est là. On doit faire quoi maintenant ?
        // Dire le comportement quand resultat =true
        // Donc ajouter +1 à la valeur dans la map pour les cases adjacentes
        // Exactement
        if (resultat) {
            // C'est quoi le premier truc à faire ?
            //Identifier les coordonnées des cases adjacentes
            // Et bien go, Fais _que ça_
            // Pair(coordonnees.first, coordonnees.second - 1)
            // Pair(coordonnees.first, coordonnees.second - 1)
            // Pair(coordonnees.first + 1, coordonnees.second - 1)
            // Pair(coordonnees.first - 1, coordonnees.second)
            // Pair(coordonnees.first + 1, coordonnees.second)
            // Pair(coordonnees.first - 1, coordonnees.second + 1)
            // Pair(coordonnees.first, coordonnees.second + 1)
            // Pair(coordonnees.first + 1, coordonnees.second + 1)

            // Y'a mieux déjà en premier lieu
            // pas faux
            // Maintenant on doit faire quoi ?
            // Essaye de formuler en pensant en mode "code"

            // J'ai les coordonnées des cases adjacentes, ...(continue la phrase)
            // Je les ajoute à ma map ? --> non, garde des termes "français", mais essaye de formuler pour que ton code soit "direct"
            // Si t'y arrives pas je te fais le début de la prochaine phrase
            // Je dis qu'elles ont une bombe à côté d'elles
            // Imaginons que c'est bon. Est-ce que tu "vois" le code en lisant ta phrase ?
            //bombCountMap.value[(coordonnees.first) to (coordonnees.second - 1)] +1 ? ^^

            // Si la bombe a été ajoutée, je calcule les coordonnées des cases adjacentes
            // Pour chacune de ces coordonnées, je regarde dans ma Map si elle existe
            // Si oui, je rajoute 1
            // Si non, je mets 1

            // Là du coup, essaye de faire le code en lisant ma phrase
            // Va dans l'ordre, pas à pas

            // La première ligne est déjà faite :

            // (coordonnees.first) to (coordonnees.second - 1)
            // (coordonnees.first) to (coordonnees.second - 1)
            // (coordonnees.first + 1) to (coordonnees.second - 1)
            // (coordonnees.first - 1) to (coordonnees.second)
            // (coordonnees.first + 1) to (coordonnees.second)
            // (coordonnees.first - 1) to (coordonnees.second + 1)
            // (coordonnees.first) to (coordonnees.second + 1)
            // (coordonnees.first + 1) to (coordonnees.second + 1)

            // pour chaque coordonnée : on en est là. Comment on peut faire ?
            // Tu as besoin de quoi pour forEach ?
            // Une range -- Oui mais pas que.
            // et un comportement -- non.
            // Tu as besoin de :
            // public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit

            // d'un Iterable. Range est iterable. List est iterable. Array est iterable. Set est iterable. etc etc

            // Donc tu as besoin d'une List.
            // Et bien crée une liste pour pouvoir faire "pour chaque coordonnée"
            // On opti un peu
            //val i = coordonnees.first
            //val j= coordonnees.second
            //listOf(
            //    i to (j - 1),
            //    i to (j - 1),
            //    (i + 1) to (j - 1),
            //    (i - 1) to j,
            //    (i + 1) to j,
            //    (i - 1) to (j + 1),
            //    i to (j + 1),
            //    (i + 1) to (j + 1),
            //)

            // Très bien. Donc on continue. "Pour chaque coordonnées, je _fais quelque chose_" :

            val i = coordonnees.first
            val j = coordonnees.second
            val adjacentCoordinates = listOf(
                i to (j - 1),
                i to (j - 1),
                (i + 1) to (j - 1),
                (i - 1) to j,
                (i + 1) to j,
                (i - 1) to (j + 1),
                i to (j + 1),
                (i + 1) to (j + 1),
            )

            // Je pense que tu t'en souviens pas mais
            // les lambdas existent
            // Je connais le mot mais je pense plus savoir à quoi il correpsond, possiblement ne jamais avoir trop compris -_-
            // Une lambda c'est une fonction anonyme (grossièrement)
            // Une lambda expression, c'est une syntaxe qui est autorisée lorsque :
            // - Une fonction a des paramètres (un ou plusieurs)
            // - Le DERNIER paramètre est une fonction
            // Dans ce cas, je peux utiliser la syntaxe lambda
            // Donc forEach attend un paramètre : forEach(action: (T) -> Unit)
            // Le type T pour l'instant on va le concrétiser
            // Dans ton cas, tu forEach sur une List<Pair<Int, Int>>, t'es d'accord ? Oui
            // Donc ton type "T" c'est Pair<Int, Int>
            // Donc ton forEach c'est ça : forEach(action: (Pair<Int, Int>) -> Unit) -- Oui
            // Donc forEach attend quoi ? - des coordonnées
            // https://media.tenor.com/dzpcXEQ09q4AAAAM/oh-no-cringe-meme.gif -- xDDDD
            // C'est quoi ce type : (Pair<Int, Int>) -> Unit ? - une paire de Int renvoyée en un truc vide -- non
            // Ca a quelle forme un type "fonction" ? () -> Unit c'est ça ? Oui.
            // Sachant ça, c'est quoi le type du paramètre du forEach ? (en textuel) une fonction ? -- Oui mais il faut quoi pour déclarer un type "fonction" à 100% ?
            // paramètres et type de retour -- oui
            // Donc ? une fonction .... ? de paramètre (Pair<Int, Int>) qui a comme retour "rien"
            // Ok, en formulation on dit plutôt : "Une fonction qui prend en paramètre une Pair<Int, Int> et qui renvoie rien" -- OK
            // Donc, on va créer une fonction qui respecte ce contrat

            //val f: (Pair<Int, Int>) -> Unit = { pair ->
            //}

            // T'es d'accord sur ça ? on l'a déclarée et définie en "même temps" là on est d'accord ? oui -- Alors j'ai compris mdr
            // Point annexe : ici on ne l'a PAS définie à la volée (c'est important pour plus tard).
            // Tu comprendras quand justement, on fera une définition à la volée

            // à la volée = "sur le moment". Tu verras. OK

            // Donc tu es d'accord que là on a une variable de type "(Pair<Int, Int>) -> Unit" ? oui
            // Et tu es également d'accord que ton forEach c'est ça : forEach(action: (Pair<Int, Int>) -> Unit) ? -- Oui
            // Donc j'ai le droit d'utiliser ma variable en paramètre du forEach vu que c'est du même type -- Oui

            //adjacentCoordinates.forEach(f)

            // f ne fait rien. Donc pour l'instant c'est nul. On pourrait rester comme ça et IMPLEMENTER f.
            // Mais on fait jamais comme ça (en tout cas, rarement).
            // Et justement, ce qu'on fait c'est qu'on fait de la définition à la volée.
            // à la volée = "sur le moment". Donc on va IMPLEMENTER LE PARAMETRE "sur le moment". C'est à dire au moment de l'appel à la fonction "forEach".
            // Ah donc la on va lui dire, tu prend le contenu de la liste, et pour chaque fois ou tu vois dans la liste un item qui est du type Pair<Int, Int>
            // il fera ce qu'il y aura entre accolades ?
            // https://imgeng.jagran.com/images/2020/mar/awkwardday1584517992710.jpg -- aaah je couine
            // On lui a déjà dit ça. Ce qu'on a fait juste au dessus c'est pareil, on va juste simplifier la syntaxe. OK

            // N'oublie pas, pour définir une fonction j'ai le droit de faire ça :
            // val f2 = {}

            // Là il se passe des choses. Oui, il s'est rendu compte de lui même que c'était une paire,
            // parce que c'est "dans le cadre" de adjacentCoordinates qui est une liste de paires ? Exactement -- Alleluia je commence à recomposer le puzzle
            // Maintenant, on passe en lambda expression. Parce que ({}) c'est chiant à lire.
            // Ce que je viens de faire n'a RIEN changé au comportement de ma ligne
            // C'est juste pour rendre ça plus lisible -- Oui, mais c'est chaud ça me perd à fond parce que j'associe () au paramètres et {} au comportement ou "Action"
            // Normal. Mais tu verras que plus tard tu feras que ça. OK. mais si tu veux pour l'instant on peut les laisser -- Merci ^^
            // Maintenant. Point très important, quand j'écris tout ça, y'a un élément implicite.
            // Je dirais même plus... implicIT -- XDDDDD c'est It du coup ^^
            // Donc là attention la phrase est énervée et reprend tout ce qu'on a vu : -- https://media.tenor.com/8Enkm9AB_i4AAAAM/intensifies-stress.gif
            // Lorsque j'implémente à la volée une lambda expression à un seul paramètre,
            // j'ai ccès au paramètre de la lambda à travers un élément implicite : it. -- je vois
            // J'enlève "ou non" parce qu'une lambda expression c'est forcément à la volée -- ok

            // Donc ici, le langage me fournit implicitement quelque chose qui s'appelle "it" et qui correspond au paramètre la lambda
            // La lambda est de type : (Pair<Int, Int>) -> Unit
            // Donc mon "it" est de type Pair<Int, Int> -- Oui
            //adjacentCoordinates.forEach({})


            // On a également une syntaxe pour forcer le renommage de ce "it", si on veut l'appeler différemment dans la lambda
            adjacentCoordinates.forEach(
                { element ->
                    // Ici j'ai renommé "it" en "element". -- oui.
                    // Ce paramètre "element", c'est quoi ? (pas en terme de type, en terme de "quoi") c'est ma liste de paires de coordonnées ?
                    // Alors, oui MAIS, précise encore.
                    // Je lis ça, je te dis "non" concrètement.
                    // Non. "element" c'est _chaque_ item de ta liste de coordonnées -- OK, c'est ce que j'avais en tête quand j'ai dis "mes coordonnées"
                    // Je m'en doutais mais j'étais pas sûr -- Chaque paire de coordonnées c'est "element"
                    // Si je veux être chiant (et je le suis) : Chaque coordonnées c'est "element" -- parce que "coordonnées" est déjà une paire -- oui -- j'ai la nuance

                    // Ok donc on est revenu à notre "pour chaque coordonnée".
                    // On doit faire:  je regarde dans ma Map si elle existe

                    // Essaye de le faire
                    // je fais "je regarde dans ma Map si elle existe"
                    // Alors ça, oui ça marche mais c'est assez "profond". Evite d'aller aussi loin dans la complexité pour l'instant.
                    // Le pb c'est que je vois rien d'autre là comme ça


                    // element in bombCountMap

                    // Ca fait quoi ça ? Aucune idée :/
                    // Ca renvoie la valeur de la Map qui est associée à la clé "element" -- OK
                    // Et il se passe quoi si la clé n'existe pas ?
                    // Ca renvoie Unit ? Non, ça renvoie null -- J'ai hésité -_-
                    // Unit c'est un objet à part.  -- Ca renvoie null parce que ça doit renvoyer un Int nullable et qu'on est dans le cas du nullable
                    // Je le mets là parce qu'après c'est intéressant : https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUgl7D9aC94MvBFkLaqIrBH0EhLuRkHMgHPT1RKP4Xdg&s
                    // Jpp
                    // Donc si on dit ?: 0 si il existe pas, ça renverra 0 ? Je sent le gif qui se pointe
                    // Ca renvoie null parce que c'est comme ça que la Map est codée. Si tu demandes une clé qui existe pas, tu reçois null. -- OK
                    // Ta phrase d'après c'est une partie de la solution
                    // - je m'en suis douté on en avait causé
                    // Avec cette syntaxe je peux METTRE A JOUR la valeur de la clé
                    // Il suffit que je la REAFFECTE
                    // Je dois la réaffecter avec quelle valeur ? On est arrivés à la dernière étape :
                    // je regarde dans ma Map si elle existe
                    // Si oui, je rajoute 1
                    // Si non, je mets 1

                    // Comment tu sais que la clé existe ? ---> Si tu demandes une clé qui existe pas, tu reçois null.
                    // si on reçoit pas null ? Ca veut dire que la clé existe - oui
                    // Donc on applique ça : Si la clé existe, je dois faire +1, sinon je dois mettre à 1 -- à 0 plutôt non ? --
                    // Bah non tu es en train de dire que y'a une bombe adjacente, donc 1, pas 0
                    // Fais JUSTE ça de manière "bête" --> SI LA CLE EXISTE. (juste cette phrase)
                    // Tu prends la valeur associée à la clé, tu lui ajoutes 1 et tu remplaces dans la Map -- la je suis paumé
                    // Je prends la valeur associée à la clé
                    //bombCountMap[element] = bombCountMap[element]
                    // Je lui ajoute 1 - et le remplacement bah c'est fait avec le "="
                    // Je mets dans la Map, associé à la clé "element", la valeur actuelle associée à "element" + 1
                    bombCountMap[element] = (bombCountMap[element] ?: 0) + 1
                    // Là on est arrivés au point de la solution que j'ai postée en spoiler hier, donc tu peux aller voir pour pas être pollué par les commentaires
                    // je viens de voir c'est bon
                    // Ca va venir, y'a énormément de choses à savoir
                    // Dis toi que là, heureusement que t'as déjà fait du dev, sinon on en serait encore
                    // "Putain val x = 4 je comprends rien"
                    // xd en vrai c'est de la logique ça va -- logique + capacité de modélisation et d'abstraction
                    // Je viens de relire avec le nom de mes variables etc etc de décomposer et j'ai compris je pourrais dire ligne par ligne ce qu'il se passe
                    // Effectivement sans les commentaires ça rassemble le puzzle et je comprend tout ce qu'on a fait - excellent -- tu m'étonnes !

                    // tu peux ajouter le if que je vois le global ?
                    // Et il partit ensuite se coucher comme un prince : https://i.imgflip.com/31k2h0.png?a475632 -- je crève de rire là
                    // T'arrives à expliquer ce que tu comprends pas ?
                    // J'essaye d'assembler avec les éléments au dessus
                    // Là le truc c'est que y'a encore des "nouveaux" concepts (if as expression, le in de Map, le return auto, etc) -- Je pense qu'on verra ça demain ^^
                    // Faut que j'intègre déjà un peu tout
                    // Oui ça fait beaucoup -- Oui
                    // Mais du coup, je te mets la vraie solution
                    //bombCountMap[element] = if (element in bombCountMap) {
                    //    bombCountMap[element] + 1
                    //} else {
                    //    1
                    //}

                }
            )
        }
    }

    val casesCoordinates = (1..GRID_SIZE).map { i ->
        (1..GRID_SIZE).map { j ->
            Case(
                bomb = (i to j) in bombCoordinates,
                adjacentBombs = bombCountMap[i to j] ?: 0,
                selected = false
            )
        }
    }
    return casesCoordinates
}


@Composable
fun Grille(modifier: Modifier = Modifier) {
    val cases by remember {
        mutableStateOf(generateGrid(bombCount = BOMB_COUNT).flatten())
    }

    Surface(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_SIZE)
        ) {
            items(cases) {
                var clicked by remember {
                    mutableStateOf(false)
                }
                Cell(Modifier.aspectRatio(1f), case = it.copy(selected = clicked)) {
                    clicked = true
                }
            }
        }
    }
}

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    case: Case,
    onClick: () -> Unit

) {
    Box(
        modifier
            .border(1.dp, Color.Black)
            .clickable {
                onClick()
            }
            .background(
                if (case.selected && case.bomb) {
                    Color.Red
                } else if (case.selected) {
                    Color.Transparent
                } else {
                    Color.LightGray
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (case.selected) {
            Text(text = case.adjacentBombs.toString())
        }
    }
}