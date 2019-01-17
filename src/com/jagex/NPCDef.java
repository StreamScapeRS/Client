package com.jagex;

public final class NPCDef {

	public static int NPCAMOUNT = 11599;
	public static int cacheIndex;
	public static Stream stream;
	public static int[] streamIndices;
	public static NPCDef[] cache;

	public static GameClient clientInstance;

	public static MemCache modelCache = new MemCache(30);

	public static NPCDef forID(int i) {
		for (int j = 0; j < 20; j++)
			if (cache[j].type == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 20;
		NPCDef npc = cache[cacheIndex] = new NPCDef();
		if (i >= streamIndices.length)
			return null;
		stream.currentOffset = streamIndices[i];
		npc.type = i;
		npc.readValues(stream);
		if (npc.name != null && npc.name.toLowerCase().contains("bank")) {
			if (npc.actions != null) {
				for (int l = 0; l < npc.actions.length; l++) {
					if (npc.actions[l] != null && npc.actions[l].equalsIgnoreCase("Collect"))
						npc.actions[l] = null;
				}
			}
		}
		npc.id = i;
		switch (i) {
		case 502:
			npc.name = "American Torva";
			npc.description = "Drops: 1:35 american set, 1:90 mbox, 1:3 mkey, 1:2 coins, 1:1 bones.";
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65394;
			npc.models[1] = 55054;
			npc.models[2] = 55052;
			npc.models[3] = 55056;
			npc.models[4] = 55058;
			npc.models[5] = 65533;
			npc.models[6] = 65530;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 75;
			npc.squaresNeeded = 1;
			break;
		case 503:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 15040;
			npc.models[1] = 15035;
			npc.models[2] = 15029;
			npc.models[3] = 15037;
			npc.models[4] = 15039;
			npc.models[5] = 15032;
			npc.models[6] = 15030;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Oreo Torva";
			npc.combatLevel = 100;
			npc.description = "Oreo themed torva.";
			break;
		case 504:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 15026;
			npc.models[1] = 15021;
			npc.models[2] = 15015;
			npc.models[3] = 15023;
			npc.models[4] = 15025;
			npc.models[5] = 15018;
			npc.models[6] = 15016;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Sky Torva";
			npc.combatLevel = 125;
			npc.description = "Sky themed torva.";
			break;
			case 2143:
				npc.name = "Baron";
				npc.description = "One of ImmortalX's veterans.";
				npc.combatLevel = 200;
				npc.actions = new String[] { null, "Attack", null, null, null };
//			npc.actions[2] = "Trade";
				npc.models = new int[7];
				npc.models[0] = 230; //HEAD
				npc.models[1] = 246; //JAW
				npc.models[2] = 10532; //hat
				npc.models[3] = 31297;//wep
				npc.models[4] = 31299;//offhand
				npc.models[5] = 20158;//offhand
				npc.models[6] = 20140;//offhand
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.originalColours[2] = 7104; //wings colour
				npc.destColours[2] = 22463; //wings colour
				break;
		case 505:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65528;
			npc.models[1] = 65523;
			npc.models[2] = 65517;
			npc.models[3] = 65525;
			npc.models[4] = 65527;
			npc.models[5] = 65520;
			npc.models[6] = 65518;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Darth Maul Torva";
			npc.combatLevel = 150;
			npc.description = "Darth Maul torva.";
			break;
		case 506:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[8];
			npc.models[0] = 119; // cape
			npc.models[1] = 115; // helm
			npc.models[2] = 117; // plate
			npc.models[3] = 113; // legs
			npc.models[4] = 31297; // weapon
			npc.models[7] = 31299; // weapon
			npc.models[5] = 121; // gloves
			npc.models[6] = 123; // boots
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Cash Torva";
			npc.combatLevel = 175;
			npc.description = "Cash themed torva.";
			break;
		case 507:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 55073;
			npc.models[1] = 55047;
			npc.models[2] = 55046;
			npc.models[3] = 55050;
			npc.models[4] = 55072;
			npc.models[5] = 65512;
			npc.models[6] = 65514;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Silver Torva";
			npc.combatLevel = 200;
			npc.description = "Silver themed torva.";
			break;

		case 8133:
			npc.name = "Shadow Corporeal Beast";
			break;

            case 1800:
                NPCDef corp = forID(8133);
                npc.name = "Shadow Corporeal beast";
                npc.actions = new String[] { null, "Pick Up", null, null, null };
                npc.models = corp.models;
                npc.standAnim = corp.standAnim;
                npc.walkAnim = corp.walkAnim;
                npc.squaresNeeded = corp.squaresNeeded;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;

			case 2222:
				npc.actions = new String[] { null, "Attack", null, null, null };
				NPCDef beast2 = forID(2745);
				npc.name = "Fire Beast";
				npc.models = beast2.models;
				npc.standAnim = beast2.standAnim;
				npc.walkAnim = beast2.walkAnim;
				npc.squaresNeeded = beast2.squaresNeeded;
				break;

			case 2223:
				NPCDef beast1 = forID(2745);
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.name = "Water Beast";
				npc.models = beast1.models;
				npc.standAnim = beast1.standAnim;
				npc.walkAnim = beast1.walkAnim;
				npc.colourRedefine2 = 100800;
				npc.squaresNeeded = beast1.squaresNeeded;
				break;
		case 509:
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 46031;
			npc.name = "Charmeleon";
			npc.combatLevel = 50;
			npc.description = "It's Charmeleon.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;

            case 93:
                npc.name = "Summoned skeleton";
                npc.actions = new String[] {null,null,null,null,null};
                npc.combatLevel = 666;
                npc.colourRedefine3 = 899;
                break;


			case 8349:
			    npc.name = "Golden Tormented demon";
			    npc.colourRedefine3 = 6500;
			    npc.sizeXZ = 175;
			    npc.sizeY = 150;
			    break;

            case 1100:
                npc.actions = new String[] { null, "Attack", null, null, null };
                npc.models = new int[8];
                npc.name = "Dagganoth Slayer";
                npc.combatLevel = 5;
                npc.description = "It's Luigi.";
                npc.models[0] = 60001; // helm
                npc.models[1] = 60003; // plate
                npc.models[2] = 60005; // legs
                npc.models[3] = 60006; // gloves
                npc.models[4] = 60008; // boots
                npc.models[5] = 60010; // wings
                npc.models[6] = 17120; // wings
                npc.standAnim = 11973;
				npc.colourRedefine3 = 50;
				npc.walkAnim = 11975;
                npc.squaresNeeded = 3;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
			case 1101:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[8];
				npc.name = "Anouke";
				npc.combatLevel = 5;
				npc.description = "It's Luigi.";
				npc.models[0] = 33001; // helm
				npc.models[1] = 33003; // plate
				npc.models[2] = 33005; // legs
				npc.models[3] = 33007; // gloves
				npc.models[4] = 33009; // boots
				npc.models[5] = 31299; // wings
				npc.models[6] = 31297; // wings
				npc.standAnim = 11973;
				npc.colourRedefine2 = 548374;
				npc.walkAnim = 11975;
				npc.squaresNeeded = 3;
				npc.sizeXZ = 180;
				npc.sizeY = 180;
				break;

		case 510:
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[8];
			npc.models[0] = 65207;
			npc.name = "Mini Purgatory";
			npc.combatLevel = 5;
			npc.description = "It's Luigi.";
            npc.models[0] = 65461; // helm
            npc.models[1] = 65459; // plate
            npc.models[2] = 65457; // legs
            npc.models[3] = 65453; // gloves
            npc.models[4] = 65455; // boots
            npc.models[5] = 65451; // wings
            npc.models[6] = 65449; // wep
            npc.models[7] = 65447; // offhand
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;

            case 500:
                npc.actions = new String[] { null, "Attack", null, null, null };
                npc.models = new int[8];
                npc.models[0] = 65207;
                npc.name = "Purgatory Boss";
                npc.combatLevel = 500;
                npc.description = "It's Luigi.";
                npc.models[0] = 65461; // helm
                npc.models[1] = 65459; // plate
                npc.models[2] = 65457; // legs
                npc.models[3] = 65453; // gloves
                npc.models[4] = 65455; // boots
                npc.models[5] = 65451; // wings
                npc.models[6] = 65449; // wep
                npc.models[7] = 65447; // offhand
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 3;
                npc.sizeXZ = 250;
                npc.sizeY = 250;
                break;

			case 550:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[8];
				npc.models[0] = 65207;
				npc.name = "Drygore God";
				npc.combatLevel = 2147;
				npc.description = "It's A God!";
				npc.models[0] = 65446; // helm
				npc.models[1] = 65444; // plate
				npc.models[2] = 65442; // legs
				npc.models[3] = 65438; // gloves
				npc.models[4] = 65440; // boots
				npc.models[5] = 65436; // wings
				npc.models[6] = 65434; // wep
				npc.standAnim = 7047;
				npc.walkAnim = 7046;
				npc.squaresNeeded = 3;
				npc.sizeXZ = 200;
				npc.sizeY = 200;
				break;

			case 6216:
				npc.sizeXZ = 400;
				npc.sizeY = 400;
				npc.squaresNeeded = 4;
				npc.combatLevel = 1000;
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.name = "Fire god";
				break;
			case 10216:
				npc.sizeXZ = 400;
				npc.sizeY = 400;
				npc.squaresNeeded = 4;
				npc.combatLevel = 1000;
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.name = "water god";
				break;

			case 600:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[8];
				npc.models[0] = 65207;
				npc.name = "Drygore God";
				npc.combatLevel = 2147;
				npc.description = "It's A God!";
				npc.models[0] = 65446; // helm
				npc.models[1] = 65444; // plate
				npc.models[2] = 65442; // legs
				npc.models[3] = 65438; // gloves
				npc.models[4] = 65440; // boots
				npc.models[5] = 65436; // wings
				npc.models[6] = 65434; // wep
				npc.standAnim = 7047;
				npc.walkAnim = 7046;
				npc.squaresNeeded = 3;
				npc.sizeXZ = 200;
				npc.sizeY = 200;
				break;

		case 511:
			npc.squaresNeeded = 1;
			npc.actions = new String[5];
			npc.actions[2] = "Trade";
			npc.models = new int[5];
			npc.models[0] = 65212; // helm
			npc.models[1] = 65213; // plate
			npc.models[2] = 65214; // legs
			npc.models[3] = 65215; // gloves
			npc.models[4] = 65216; // boots
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "StreamScape Point Store";
			npc.description = "StreamScape Point Store";
			break;
		case 515:
			npc.squaresNeeded = 4;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65408;
			npc.models[1] = 65415;
			npc.models[2] = 65413;
			npc.models[3] = 65417;
			npc.models[4] = 65419;
			npc.models[5] = 65411;
			npc.models[6] = 65410;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Camouflage Torva Boss";
			npc.combatLevel = 225;
			npc.description = "Camouflage themed torva boss.";
			npc.sizeXZ = 235;
			npc.sizeY = 235;
			break;
		case 517:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 3155;
			npc.models[1] = 2684;
			npc.models[2] = 2575;
			npc.models[3] = 2547;
			npc.models[4] = 24007;
			npc.models[5] = 2714;
			npc.models[6] = 14343;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Winter Torva Boss";
			npc.combatLevel = 250;
			npc.description = "Winter camo themed torva boss.";
			npc.sizeXZ = 235;
			npc.sizeY = 235;
			break;
		case 518:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 265;
			npc.models[1] = 2385;
			npc.models[2] = 17297;
			npc.models[3] = 202;
			npc.models[4] = 1314;
			npc.models[5] = 2521;
			npc.models[6] = 2483;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Bloodshot Torva Boss";
			npc.combatLevel = 300;
			npc.description = "Bloodshot camo themed torva boss.";
			npc.sizeXZ = 235;
			npc.sizeY = 235;
			break;

		case 998:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65408;
			npc.models[1] = 65415;
			npc.models[2] = 65413;
			npc.models[3] = 65417;
			npc.models[4] = 65419;
			npc.models[5] = 65411;
			npc.models[6] = 65410;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Camouflage Mini Boss";
			npc.combatLevel = 225;
			npc.description = "Camouflage themed mini boss.";
			break;
		case 999:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 3155;
			npc.models[1] = 2684;
			npc.models[2] = 2575;
			npc.models[3] = 2547;
			npc.models[4] = 24007;
			npc.models[5] = 2714;
			npc.models[6] = 14343;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Winter Mini Boss";
			npc.combatLevel = 250;
			npc.description = "Winter camo themed mini boss.";
			break;
		case 1000:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.models = new int[7];
			npc.models[0] = 265;
			npc.models[1] = 2385;
			npc.models[2] = 17297;
			npc.models[3] = 202;
			npc.models[4] = 1314;
			npc.models[5] = 2521;
			npc.models[6] = 2483;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Bloodshot Mini Boss";
			npc.combatLevel = 300;
			npc.description = "Bloodshot camo themed mini boss.";
			break;

		case 605:
			npc.actions = new String[] { "Talk-to", null, "Vote Rewards", "Loyalty Titles", null };
			npc.models = new int[1];
			npc.models[0] = 28233; // 65205;
			npc.name = "Squirtle";
			npc.description = "It's Squirtle.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;

            case 1600:
                npc.actions = new String[] { "Pick-up", "Pick-up", null, null, null };
                npc.models = new int[1];
                npc.models[0] = 28233; // 65205;
                npc.name = "Squirtle pet";
                npc.description = "It's Squirtle.";
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 100;
                break;


            case 519:
			npc.models = new int[1];
			npc.models[0] = 28233;
			npc.name = "Squirtle";
			npc.description = "It's squirtle.";
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.combatLevel = 85;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 666:
			npc.models = new int[1];
			npc.models[0] = 12354;
			npc.name = "Ichigo";
			npc.description = "It's squirtle.";
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.combatLevel = 85;
			npc.standAnim = 12021;
			npc.walkAnim = 12023;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			break;

		case 669:
			npc.models = new int[1];
			npc.models[0] = 12354;
			npc.name = "Zangetsu";
			npc.description = "It's Zangetsu.";
			npc.actions = new String[] { null, "Attack", null, null, null };
			npc.combatLevel = 85;
			npc.standAnim = 12021;
			npc.walkAnim = 12023;
			npc.colourRedefine = 127;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			break;

            case 700:
                npc.models = new int[1];
                npc.models[0] = 12354;
                npc.name = "Ichigo";
                npc.description = "It's squirtle.";
                npc.actions = new String[] { null, "Pick Up", null, null, null };
                npc.combatLevel = 85;
                npc.standAnim = 12021;
                npc.walkAnim = 12023;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;

            case 701:
                npc.models = new int[1];
                npc.models[0] = 12354;
                npc.name = "Zangetsu";
                npc.description = "It's Zangetsu.";
                npc.actions = new String[] { null, "Pick Up", null, null, null };
                npc.combatLevel = 85;
                npc.standAnim = 12021;
                npc.walkAnim = 12023;
                npc.colourRedefine = 127;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;

            case 3000:
                npc.name = "@gr3@Christmas tree";
                npc.actions = new String[] { null, "Search", null, null, null };
                npc.models[0] = 62446;
                npc.squaresNeeded = 3;
                npc.walkAnim = 0;
                npc.standAnim = 0;

                break;

            case 5748:
                npc.name = "@red@Presents";
                npc.actions = new String[] { null, "Search", null, null, null };
                npc.models[0] = 51180;
                npc.walkAnim = 0;
                npc.standAnim = 0;
                npc.squaresNeeded =  2;
                break;

            case 5749:
                npc.name = "@mag@Presents";
                npc.actions = new String[] { null, "Search", null, null, null };
                npc.models[0] = 51180;
                npc.walkAnim = 0;
                npc.standAnim = 0;
                npc.squaresNeeded = 2;

                break;

		case 3101:
			npc.actions = new String[] { "Talk-to", null, "Start", "Rewards", null };
			npc.models = new int[1];
			npc.models[0] = 65206;
			npc.name = "Zombie Store";
			npc.description = "It's Pikachu.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		// explorer jack
		case 7969:
			npc.actions = new String[] { "Talk-to", null, "Trade", null, null };
			break;
		case 4249:
			npc.name = "Gambler";
			break;
		// summoning store
		case 6970:
			npc.actions = new String[] { "Trade", null, "Exchange Shards", null, null };
			break;
		case 4657:
			npc.actions = new String[] { "Trade", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 58482;
			npc.name = "@gr1@Donation @gr2@Points @gr3@Store";
			npc.description = "It's Mr. Krabs.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;

            npc.squaresNeeded = 3;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 6139:
			npc.models = new int[1];
			npc.models[0] = 2781;
			npc.name = "Account Handler";

            npc.description = "It's Lucario.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		// nomad
		case 8591:
			npc.actions = new String[] { "Talk-to", null, "Trade", null, null };
			break;
		case 316:
		case 315:
		case 309:
		case 310:
		case 314:
		case 312:
		case 313:
			npc.sizeXZ = 30;
			break;
		case 318:
			npc.sizeXZ = 30;
			npc.actions = new String[] { "Net", null, "Lure", null, null };
			break;
		// crafting store
		case 805:
			npc.actions = new String[] { "Trade", null, "Tan hide", null, null };
			break;
		// runecrafting store
		case 461:
			npc.name = "Runecrafting Supplies";
			// warrior armor store, not sure what it's for.
		case 650:
			// hunting store
		case 5112:
			// void knight exchange
		case 3789:
			// Brother jered (prayer store)
		case 802:
			// general store
		case 520:
			// shop assasistant
		case 521:
			// dung store?
		case 11226:
			npc.actions = new String[] { "Trade", null, null, null, null };
			break;
		// donor store
		case 8444:
			npc.actions = new String[5];
			npc.actions[0] = "Trade";
			break;
		case 2579:
			npc.combatLevel = 0;
			npc.actions = new String[] { "Talk-to", null, "Trade", null, null };
			npc.models = new int[1];
			npc.models[0] = 58518;
			npc.name = "Prestige Store";
			npc.description = "It's Sonic.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;

            npc.squaresNeeded = 1;
			break;
		case 6830:
		case 6841:
		case 6796:
		case 7331:
		case 6831:
		case 7361:
		case 6847:
		case 6872:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7333:
		case 7351:
		case 7367:
		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
		case 9481:
		case 6827:
		case 6889:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6799:
		case 7335:
		case 7347:
		case 6800:
		case 9488:
		case 6804:
		case 6822:
		case 6849:
		case 7355:
		case 7357:
		case 7359:
		case 7341:
		case 7329:
		case 7339:
		case 7349:
		case 7375:
		case 7343:
		case 6820:
		case 6865:
		case 6809:
		case 7363:
		case 7337:
		case 7365:
		case 6991:
		case 6992:
		case 6869:
		case 6818:
		case 6843:
		case 6823:
		case 7377:
		case 6887:
		case 6885:
		case 6883:
		case 6881:
		case 6879:
		case 6877:
		case 6875:
		case 6833:
		case 6851:
		case 5079:
		case 5080:
		case 6824:
			npc.actions = new String[] { null, null, null, null, null };
			break;
		case 6806:
		case 6807:
		case 6994:
		case 6995:
		case 6867:
		case 6868:
		case 6794:
		case 6795:
		case 6815:
		case 6816:
		case 6874:
		case 6873:
		case 3594:
		case 3590:
		case 3596:
			npc.actions = new String[] { "Store", null, null, null, null };
			break;
		// fancy clothes store
		case 548:
			npc.actions = new String[] { "Trade", null, null, null, null };
			break;
		// farming store
		case 3299:
			// agility store
		case 437:
			npc.actions = new String[] { "Trade", null, null, null, null };
			break;
		// herblore store
		case 8459:
			npc.drawMinimapDot = true;
			break;
		case 961:
			npc.actions = new String[] { null, null, "Buy Consumables", "Restore Stats", null };
			npc.name = "Healer";
			break;
		case 705:
			npc.actions = new String[] { "Buy Armour", null, "Buy Weapons", null, "Buy Jewelries" };
			npc.name = "Warrior";
			break;
		case 1861:
			npc.actions = new String[] { null, null, "Buy Equipment", null, "Buy Ammunition", null };
			npc.name = "Archer";
			break;
		case 946:
			npc.actions = new String[] { null, null, "Buy Equipment", "Buy Runes", null };
			npc.name = "Mage";
			break;
		// skillcape store
		case 2253:
			npc.actions = new String[] { null, null, "Buy Skillcapes", "Buy Skillcapes (t)", "Buy Hoods" };
			break;
		case 2292:
			npc.actions = new String[] { "Trade", null, null, null, null };
			npc.name = "Merchant";
			break;
		case 2676:
			npc.actions = new String[] { "Makeover", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 58481;
			npc.name = "Character Makeover";
			npc.description = "It's Homer.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
            npc.squaresNeeded = 1;
			break;
		// banker
		case 1360:
			npc.actions = new String[] { "Talk-to", null, null, null, null };
			break;
		case 1685:
			npc.name = "Pure";
			npc.actions = new String[] { "Trade", null, null, null, null };
			break;
		// PETS
		case 522:
			npc.name = "American Torva Pet";
			npc.description = "American themed torva pet.";
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65394;
			npc.models[1] = 55054;
			npc.models[2] = 55052;
			npc.models[3] = 55056;
			npc.models[4] = 55058;
			npc.models[5] = 65533;
			npc.models[6] = 65530;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 15;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 523:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 15040;
			npc.models[1] = 15035;
			npc.models[2] = 15029;
			npc.models[3] = 15037;
			npc.models[4] = 15039;
			npc.models[5] = 15032;
			npc.models[6] = 15030;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Oreo Torva Pet";
			npc.combatLevel = 100;
			npc.description = "Oreo themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 524:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 15026;
			npc.models[1] = 15021;
			npc.models[2] = 15015;
			npc.models[3] = 15023;
			npc.models[4] = 15025;
			npc.models[5] = 15018;
			npc.models[6] = 15016;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Sky Torva Pet";
			npc.combatLevel = 25;
			npc.description = "Sky themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;

            case 1475:
                NPCDef kree = NPCDef.forID(6222);
                npc.models = new int [2];

                npc.models[0] = kree.models[0];
                npc.models[1] = kree.models[1];

                npc.standAnim = kree.standAnim;
                npc.walkAnim = kree.walkAnim;
                npc.sizeXZ = kree.sizeXZ-25;
                npc.sizeY = kree.sizeY-25;
                npc.squaresNeeded = kree.squaresNeeded;
                npc.colourRedefine3 = 97000;
                break;

            case 1476:
                npc.models = new int [4];
                npc.models[0] = NPCDef.forID(6225).models[0];
                npc.models[1] = NPCDef.forID(6225).models[1];
                npc.models[2] = NPCDef.forID(6225).models[2];
                npc.models[3] = NPCDef.forID(6225).models[3];
                npc.standAnim = NPCDef.forID(6225).standAnim;
                npc.walkAnim = NPCDef.forID(6225).walkAnim;
                npc.sizeXZ = NPCDef.forID(6225).sizeXZ;
                npc.sizeY = NPCDef.forID(6225).sizeY;
                npc.colourRedefine3 = 97000;
                break;
		case 525:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65528;
			npc.models[1] = 65523;
			npc.models[2] = 65517;
			npc.models[3] = 65525;
			npc.models[4] = 65527;
			npc.models[5] = 65520;
			npc.models[6] = 65518;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Darth Maul Torva Pet";
			npc.combatLevel = 30;
			npc.description = "Darth Maul torva Pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 526:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 119; // cape
			npc.models[1] = 115; // helm
			npc.models[2] = 117; // plate
			npc.models[3] = 113; // legs
			npc.models[4] = 111; // weapon
			npc.models[5] = 121; // gloves
			npc.models[6] = 123; // boots
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Cash Torva Pet";
			npc.combatLevel = 35;
			npc.description = "Cash themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 527:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 55073;
			npc.models[1] = 55047;
			npc.models[2] = 55046;
			npc.models[3] = 55050;
			npc.models[4] = 55072;
			npc.models[5] = 65512;
			npc.models[6] = 65514;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Silver Torva Pet";
			npc.combatLevel = 40;
			npc.description = "Silver themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 528:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 65408;
			npc.models[1] = 65415;
			npc.models[2] = 65413;
			npc.models[3] = 65417;
			npc.models[4] = 65419;
			npc.models[5] = 65411;
			npc.models[6] = 65410;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Camouflage Torva Pet";
			npc.combatLevel = 50;
			npc.description = "Camouflage themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 529:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 3155;
			npc.models[1] = 2684;
			npc.models[2] = 2575;
			npc.models[3] = 2547;
			npc.models[4] = 2476;
			npc.models[5] = 2714;
			npc.models[6] = 14343;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Winter Camo Torva Pet";
			npc.combatLevel = 65;
			npc.description = "Winter camo themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 530:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[7];
			npc.models[0] = 265;
			npc.models[1] = 2385;
			npc.models[2] = 17297;
			npc.models[3] = 202;
			npc.models[4] = 1314;
			npc.models[5] = 2521;
			npc.models[6] = 2483;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "Bloodshot Camo Torva Pet";
			npc.combatLevel = 100;
			npc.description = "Bloodshot camo themed torva pet.";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 531:
			npc.actions = new String[] { null, "Pick Up", null, null, null };
			npc.models = new int[1];
			npc.models[0] = 58467;
			npc.name = "MewTwo Pet";
			npc.combatLevel = 25;
			npc.description = "It's MewTwo pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
			case 508:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[1];
				npc.models[0] = 58467;
				npc.name = "MewTwo";
				npc.combatLevel = 25;
				npc.description = "It's MewTwo.";
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 50;
				npc.sizeY = 75;
				break;
		case 532:
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 46031;
			npc.name = "Charmeleon Pet";
			npc.combatLevel = 25;
			npc.description = "It's Charmeleon pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 533:
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 65207;
			npc.name = "Luigi Pet";
			npc.combatLevel = 5;
			npc.description = "It's Luigi Pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 534:
			npc.squaresNeeded = 1;
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[7];
			npc.models[1] = 65496;
			npc.models[2] = 65494;
			npc.models[3] = 65498;
			npc.models[5] = 65492;
			npc.models[6] = 65491;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.name = "StreamScape Bank Pet";
			npc.description = "StreamScape Bank Pet";
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 535:
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 58481;
			npc.name = "Homer Pet";
			npc.description = "Homer Pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 537:
			npc.combatLevel = 0;
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 58518;
			npc.name = "Sonic Pet";
			npc.description = "It's Sonic Pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 538:
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 58482;
			npc.name = "Mr. Krabbs Pet";
			npc.description = "It's Mr. Krabs pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 539:
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.models = new int[1];
			npc.models[0] = 2781;
			npc.name = "Lucario Pet";
			npc.description = "It's Lucario pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
			case 1200:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 6813;
				npc.models[1] = 55744;
				npc.models[2] = 44812;
				npc.models[3] = 44768;
				npc.models[4] = 55703;
				npc.models[5] = 4953;
				npc.colourRedefine3 = 899;
				npc.name = "@red@Fire Draconian range";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(6220).standAnim;
				npc.walkAnim = NPCDef.forID(6220).walkAnim;
				npc.squaresNeeded = NPCDef.forID(6220).squaresNeeded;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;

			case 1201:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 55746; //helm
				npc.models[1] = 42627; //body
				npc.models[2] = 42634; //legs
				npc.models[3] = 55681; //boots
				npc.models[4] = 4953; //gloves
				npc.models[5] = 55909; //stafff

				npc.colourRedefine3 = 900;
				npc.name = "@red@Fire Draconian mage";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(912).standAnim;
				npc.walkAnim = NPCDef.forID(912).walkAnim;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;


			case 1202:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 6813;
				npc.models[1] = 55744;
				npc.models[2] = 44812;
				npc.models[3] = 44768;
				npc.models[4] = 55703;
				npc.models[5] = 4953;
				npc.colourRedefine3 = 97000;
				npc.name = "@cya@Ice Draconian range";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(6220).standAnim;
				npc.walkAnim = NPCDef.forID(6220).walkAnim;
				npc.squaresNeeded = NPCDef.forID(6220).squaresNeeded;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;

			case 1203:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 55746; //helm
				npc.models[1] = 42627; //body
				npc.models[2] = 42634; //legs
				npc.models[3] = 55681; //boots
				npc.models[4] = 4953; //gloves
				npc.models[5] = 55909; //stafff

				npc.colourRedefine3 = 97000;
				npc.name = "@cya@Ice Draconian mage";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(912).standAnim;
				npc.walkAnim = NPCDef.forID(912).walkAnim;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;

			case 1204:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 6813;
				npc.models[1] = 55744;
				npc.models[2] = 44812;
				npc.models[3] = 44768;
				npc.models[4] = 55703;
				npc.models[5] = 4953;
				npc.colourRedefine3 = 210770;
				npc.name = "@gre@Toxic Draconian range";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(6220).standAnim;
				npc.walkAnim = NPCDef.forID(6220).walkAnim;
				npc.squaresNeeded = NPCDef.forID(6220).squaresNeeded;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;

			case 898:
				npc.name = "Crystal spider";
				npc.sizeXZ = 300;
				npc.sizeY = 300;
				npc.colourRedefine3 = 220000;
   			break;

			case 900:
				npc.name = "Crystal wolf";
				npc.sizeXZ = 180;
				npc.sizeY = 180;
				npc.colourRedefine3 = 180000;
				break;
			case 1206:
				npc.actions = new String[] { null, "Attack", null, null, null };
				npc.models = new int[6];
				npc.models[0] = 55746; //helm
				npc.models[1] = 42627; //body
				npc.models[2] = 42634; //legs
				npc.models[3] = 55681; //boots
				npc.models[4] = 4953; //gloves
				npc.models[5] = 55909; //stafff

				npc.colourRedefine3 = 210770;
				npc.name = "@gre@Toxic Draconian mage";
				npc.description = "It's Lucario pet.";
				npc.standAnim = NPCDef.forID(912).standAnim;
				npc.walkAnim = NPCDef.forID(912).walkAnim;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;

			case 1300:
				npc.models = new int[1];
				npc.models[0] = 2426;
				npc.name = "@bla@M@whi@y@bla@s@whi@t@bla@e@whi@r@bla@y@whi@ @bla@B@whi@o@bla@x@whi@";
				npc.squaresNeeded = 2;
				npc.standAnim = -1;
				npc.walkAnim = -1;
				npc.destColours = new int[] { 12, 6, 63 };
				npc.originalColours = new int[] { 22410, 926, 2999 };
				npc.sizeXZ = 350;
				npc.sizeY = 350;
				npc.actions = new String[] { null, "Attack", null, null, null };

				break;

		case 540:
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 65205;
			npc.name = "Spongebob Pet";
			npc.description = "It's Spongebob pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 541:
			npc.models = new int[1];
			npc.models[0] = 28233;
			npc.name = "Squirtle Pet";
			npc.description = "It's squirtle pet.";
			npc.actions = new String[] { null, "Pick Up", null, null, null };

			npc.combatLevel = 85;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 542:
			npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 65206;
			npc.name = "Pikachu Pet";
			npc.description = "It's Pikachu Pet.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		}
		return npc;
	}

	public static void nullLoader() {
		modelCache = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public static void unpackConfig(CacheArchive streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs];
		int i = 2;
		for (int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}
		cache = new NPCDef[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new NPCDef();
	}

	public int frontLight = 68;

	public int backLight = 820;

	public int rightLight = 0;

	public int middleLight = -1;

	public int leftLight = 0;

	public int turn90CCWAnimIndex;
	public int varbitId;
	public int turn180AnimIndex;
	public int varSettingsId;
	public int combatLevel;
	public String name;
	public String actions[];
	public int walkAnim;
	public int runAnim;
	public byte squaresNeeded;
	public int[] destColours;
	public int[] npcHeadModels;
	public int headIcon;
	public int[] originalColours;
	public int standAnim;
	public long type;
	public int degreesToTurn;
	public int turn90CWAnimIndex;
	public boolean clickable;
	public int lightning;
	public int sizeY;
	public int colourRedefine = 0;
	public int colourRedefine2 = 0;
	public int colourRedefine3 = 0;
	public boolean drawMinimapDot;
	public int childrenIDs[];
	public String description;
	public int sizeXZ;
	public int shadow;
	public boolean hasRenderPriority;
	public int[] models;
	public int id;

	public NPCDef() {
		turn90CCWAnimIndex = -1;
		varbitId = -1;
		turn180AnimIndex = -1;
		varSettingsId = -1;
		combatLevel = -1;
		walkAnim = -1;
		squaresNeeded = 1;
		headIcon = -1;
		standAnim = -1;
		type = -1L;
		degreesToTurn = 32;
		turn90CWAnimIndex = -1;
		clickable = true;
		sizeY = 128;
		drawMinimapDot = true;
		sizeXZ = 128;
		hasRenderPriority = false;
	}

	public NPCDef getAlteredNPCDef() {
		try {
			int j = -1;
			if (varbitId != -1) {
				VarBit varBit = VarBit.cache[varbitId];
				int k = varBit.configId;
				int l = varBit.leastSignificantBit;
				int i1 = varBit.mostSignificantBit;
				int j1 = GameClient.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else if (varSettingsId != -1) {
				j = clientInstance.variousSettings[varSettingsId];
			}
			if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Model getAnimatedModel(int j, int k, int ai[]) {
		if (childrenIDs != null) {
			NPCDef npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.getAnimatedModel(j, k, ai);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}

			if (colourRedefine > 0) {
				completedModel.method1337(colourRedefine);
			}
			if (colourRedefine2 != 0) {
				completedModel.method1338(colourRedefine2);
			}
			if (colourRedefine3 != 0) {
				completedModel.method1339(colourRedefine3);
			}

			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(k) & FrameReader.isNullFrame(j));
		if (k != -1 && j != -1)
			animatedModel.method471(ai, j, k);
		else if (k != -1)
			animatedModel.applyTransform(k);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.triangleSkin = null;
		animatedModel.vertexSkin = null;
		if (squaresNeeded == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public Model getHeadModel() {
		if (childrenIDs != null) {
			NPCDef altered = getAlteredNPCDef();
			if (altered == null)
				return null;
			else
				return altered.getHeadModel();
		}
		if (npcHeadModels == null)
			return null;
		boolean everyFetched = false;
		for (int i = 0; i < npcHeadModels.length; i++)
			if (!Model.modelIsFetched(npcHeadModels[i]))
				everyFetched = true;
		if (everyFetched)
			return null;
		Model parts[] = new Model[npcHeadModels.length];
		for (int j = 0; j < npcHeadModels.length; j++)
			parts[j] = Model.fetchModel(npcHeadModels[j]);
		Model completeModel;
		if (parts.length == 1)
			completeModel = parts[0];
		else
			completeModel = new Model(parts.length, parts);
		if (originalColours != null) {
			for (int k = 0; k < originalColours.length; k++)
				completeModel.recolour(originalColours[k], destColours[k]);
		}
		if (colourRedefine > 0) {
			completeModel.method1337(colourRedefine);
		}
		if (colourRedefine2 != 0) {
			completeModel.method1338(colourRedefine2);
		}
		if (colourRedefine3 != 0) {
			completeModel.method1339(colourRedefine3);
		}

		return completeModel;
	}

	public Model method164(int j, int frame, int ai[], int nextFrame, int idk, int idk2) {
		if (childrenIDs != null) {
			NPCDef npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.method164(j, frame, ai, nextFrame, idk, idk2);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			if (colourRedefine > 0) {
				completedModel.method1337(colourRedefine);
			}
			if (colourRedefine2 != 0) {
				completedModel.method1338(colourRedefine2);
			}
			if (colourRedefine3 != 0) {
				completedModel.method1339(colourRedefine3);
			}

			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(frame) & FrameReader.isNullFrame(j));

		if (frame != -1 && j != -1)
			animatedModel.method471(ai, j, frame);
		else if (frame != -1 && nextFrame != -1)
			animatedModel.applyTransform(frame, nextFrame, idk, idk2);
		else if (frame != -1)
			animatedModel.applyTransform(frame);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.triangleSkin = null;
		animatedModel.vertexSkin = null;
		if (squaresNeeded == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				int j = stream.readUnsignedByte();
				models = new int[j];
				for (int j1 = 0; j1 < j; j1++)
					models[j1] = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readNewString();
			else if (i == 3) {
				description = stream.readNewString();
			} else if (i == 12)
				squaresNeeded = stream.readSignedByte();
			else if (i == 13)
				standAnim = stream.readUnsignedWord();
			else if (i == 14) {
				walkAnim = stream.readUnsignedWord();
				runAnim = walkAnim;
			} else if (i == 17) {
				walkAnim = stream.readUnsignedWord();
				turn180AnimIndex = stream.readUnsignedWord();
				turn90CWAnimIndex = stream.readUnsignedWord();
				turn90CCWAnimIndex = stream.readUnsignedWord();
				if (walkAnim == 65535)
					walkAnim = -1;
				if (turn180AnimIndex == 65535)
					turn180AnimIndex = -1;
				if (turn90CWAnimIndex == 65535)
					turn90CWAnimIndex = -1;
				if (turn90CCWAnimIndex == 65535)
					turn90CCWAnimIndex = -1;
			} else if (i >= 30 && i < 40) {
				if (actions == null)
					actions = new String[5];
				actions[i - 30] = stream.readNewString();
				if (actions[i - 30].equalsIgnoreCase("hidden"))
					actions[i - 30] = null;
			} else if (i == 40) {
				int k = stream.readUnsignedByte();
				destColours = new int[k];
				originalColours = new int[k];
				for (int k1 = 0; k1 < k; k1++) {
					originalColours[k1] = stream.readUnsignedWord();
					destColours[k1] = stream.readUnsignedWord();
				}

			} else if (i == 60) {
				int l = stream.readUnsignedByte();
				npcHeadModels = new int[l];
				for (int l1 = 0; l1 < l; l1++)
					npcHeadModels[l1] = stream.readUnsignedWord();
			} else if (i == 90)
				stream.readUnsignedWord();
			else if (i == 91)
				stream.readUnsignedWord();
			else if (i == 92)
				stream.readUnsignedWord();
			else if (i == 93)
				drawMinimapDot = false;
			else if (i == 95)
				combatLevel = stream.readUnsignedWord();
			else if (i == 97)
				sizeXZ = stream.readUnsignedWord();
			else if (i == 98)
				sizeY = stream.readUnsignedWord();
			else if (i == 99)
				hasRenderPriority = true;
			else if (i == 100)
				lightning = stream.readSignedByte();
			else if (i == 101)
				shadow = stream.readSignedByte() * 5;
			else if (i == 102)
				headIcon = stream.readUnsignedWord();
			else if (i == 103)
				degreesToTurn = stream.readUnsignedWord();
			else if (i == 106) {
				varbitId = stream.readUnsignedWord();
				if (varbitId == 65535)
					varbitId = -1;
				varSettingsId = stream.readUnsignedWord();
				if (varSettingsId == 65535)
					varSettingsId = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for (int i2 = 0; i2 <= i1; i2++) {
					childrenIDs[i2] = stream.readUnsignedWord();
					if (childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}
			} else if (i == 107)
				clickable = false;
		} while (true);
	}
}