package com.jagex;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class ItemDef {

	private static int[] prices;

	public int colourRedefine = 0;
	public int colourRedefine2 = 0;
	public int colourRedefine3 = 0;

	private static List<Integer> untradeableItems = new ArrayList<Integer>();

	public static final int[] UNTRADEABLE_ITEMS = { 0 };

	public static MemCache spriteCache = new MemCache(100);

	public static MemCache modelCache = new MemCache(50);

	public static ItemDef[] cache;

	public static int cacheIndex;

	public static Stream stream;

	public static int[] streamIndices;

	public static int totalItems;

	public static String itemModels(int itemID) {
		int inv = forID(itemID).modelID;
		int male = forID(itemID).maleEquip1;
		int male2 = forID(itemID).maleEquip2;
		int male3 = forID(itemID).maleEquip3;
		int female = forID(itemID).femaleEquip1;
		int female2 = forID(itemID).femaleEquip1;
		int female3 = forID(itemID).femaleEquip1;
		String name = forID(itemID).name;
		return "<col=225>"+name+"</col> (<col=800000000>"+itemID+"</col>) - [inv: <col=800000000>"+inv+"</col>] - [male: <col=800000000>"+male+"</col>] - [male: <col=800000000>"+male2+"</col>]- [male: <col=800000000>\"+male3+\"</col>]  - [female: <col=800000000>"+female+"</col>]";
	}

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		if (i >= streamIndices.length) {
			itemDef.id = 1;
			itemDef.setDefaults();
			return itemDef;
		}
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lentItemID != -1)
			itemDef.toLend();
		if (itemDef.id == i && itemDef.editedModelColor == null) {
			itemDef.editedModelColor = new int[1];
			itemDef.newModelColor = new int[1];
			itemDef.editedModelColor[0] = 0;
			itemDef.newModelColor[0] = 1;
		}
		if (untradeableItems.contains(itemDef.id)) {
			itemDef.untradeable = true;
		}
		itemDef.value = prices[itemDef.id];
		if (itemDef.editedModelColor != null) {
			for (int i2 = 0; i2 < itemDef.editedModelColor.length; i2++) {
				if (itemDef.newModelColor[i2] == 0) {
					itemDef.newModelColor[i2] = 1;
				}
			}
		}
		switch (i) {

		case 295:
			itemDef.modelID = 2515;
			itemDef.name = "Silver Chain";
			itemDef.description = "Silver Chain";
			itemDef.modelZoom = 620;
			itemDef.modelOffsetY = 16;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 68;
			itemDef.rotationY = 424;
			itemDef.maleEquip1 = 283;
			itemDef.femaleEquip1 = 283;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 1647:
			itemDef.modelID = 3679; // 2807;
			itemDef.name = "American torva pet";
			itemDef.description = "American torva pet";
			itemDef.modelZoom = 1250;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 0;
			itemDef.rotationY = 320;
			itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
			itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
			break;

		case 17010:
			itemDef.modelID = 12354; // 2807;
			itemDef.name = "Ichigo pet";
			itemDef.description = "American torva pet";
			itemDef.modelZoom = 1750;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 0;
			itemDef.rotationY = 320;
			itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
			itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
			break;

		case 17011:
			itemDef.modelID = 12354; // 2807;
			itemDef.name = "Zangetsu pet";
			itemDef.description = "American torva pet";
            itemDef.colourRedefine = 127;
            itemDef.modelZoom = 1750;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 0;
			itemDef.rotationY = 320;
			itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
			itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
			break;

		case 17013:
		   NPCDef corp = NPCDef.forID(8133);

			itemDef.modelID = corp.models[0]; // 2807;
			itemDef.name = "Shadow Corporeal pet";
			itemDef.description = "American torva pet";
            itemDef.modelZoom = 7000;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 50;
			itemDef.rotationY = 320;
			itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
			itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
			break;


            case 17015:
			itemDef.modelID = 28233; // 2807;
			itemDef.name = "Squirtle pet";
			itemDef.description = "American torva pet";
            itemDef.modelZoom = 2200;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 100;
			itemDef.rotationY = 320;
			itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
			itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
			break;

            case 17017:
                itemDef.modelID = 58482; // 2807;
                itemDef.name = "Mr. Krabs pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 3500;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = -3;
                itemDef.rotationX = 0;
                itemDef.rotationY = 320;
                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;

            case 17019:
                itemDef.modelID = 2781; // 2807;
                itemDef.name = "Lucario pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 1800;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = 25;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelOffsetX = 50;

                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;

            case 17021:
                itemDef.modelID = 58518; // 2807;
                itemDef.name = "Sonic pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 1700;
                itemDef.modelOffsetY = 10;
                itemDef.modelOffset1 = -3;
                itemDef.rotationX = 10;
                itemDef.rotationY = 0;
                itemDef.modelOffsetX = 50;
                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;

            case 17023:
                itemDef.modelID = 58467; // 2807;
                itemDef.name = "Mewtwo pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 3200;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = 50;
                itemDef.modelOffsetX = 50;
                itemDef.rotationX = 0;
                itemDef.rotationY = 320;
                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;

            case 17027:
                itemDef.modelID = 58481; // 2807;
                itemDef.name = "Homer pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 1700;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffsetX = 50;
                itemDef.modelOffset1 = 25;
                itemDef.rotationX = 0;
                itemDef.rotationY = 320;
                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;
            case 17029:
                itemDef.modelID = 65207; // 2807;
                itemDef.name = "Luigi pet";
                itemDef.description = "American torva pet";
                itemDef.modelZoom = 1700;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffsetX = 50;
                itemDef.modelOffset1 = 25;
                itemDef.rotationX = 0;
                itemDef.rotationY = 320;
                itemDef.groundActions = new String[] { null, null, "Pick Up", null, null };
                itemDef.actions = new String[] { "Summon", null, null, null, "Drop" };
                break;

		case 14667:
			itemDef.name = "Zombie fragment";
			itemDef.modelID = ItemDef.forID(14639).modelID;
			break;
		case 19670:
			itemDef.name = "Vote scroll";
			itemDef.description = "Claim the scroll for a reward.";
			break;
		case 2996:
			itemDef.name = "Agility ticket";
			break;
		case 5510:
		case 5512:
		case 5509:
			itemDef.actions = new String[] { "Fill", null, "Empty", "Check", null, null };
			break;
		case 11998:
			itemDef.name = "Scimitar";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			break;
		case 11999:
			itemDef.name = "Scimitar";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			itemDef.modelZoom = 700;
			itemDef.rotationX = 0;
			itemDef.rotationY = 350;
			itemDef.modelID = 2429;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 11998;
			itemDef.certTemplateID = 799;
			break;
		case 1389:
			itemDef.name = "Staff";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			break;
		case 1390:
			itemDef.name = "Staff";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			break;
		case 17401:
			itemDef.name = "Damaged Hammer";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			break;
		case 17402:
			itemDef.name = "Damaged Hammer";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			itemDef.modelZoom = 760;
			itemDef.rotationX = 28;
			itemDef.rotationY = 552;
			itemDef.modelID = 2429;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 17401;
			itemDef.certTemplateID = 799;
			break;
		case 15078:
			itemDef.name = "Ring of Morphing";
			itemDef.actions = new String[] { null, "Wear", null, "Morph", "Drop", null };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.modelZoom = 592;
			itemDef.modelOffsetX = 1697;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = -14;
			itemDef.rotationX = 1860;
			itemDef.rotationY = 1885;
			break;
		case 4202:
			itemDef.name = "Ring of Fortune";
			itemDef.actions = new String[] { null, "Wear", null, "Morph", "Drop", null };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.modelZoom = 600;
			itemDef.modelOffsetY = 54;
			itemDef.modelOffset1 = -4;
			itemDef.rotationX = 1093;
			itemDef.rotationY = 1233;
			break;
		case 15009:
			itemDef.name = "Gold Ring";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			break;
		case 15010:
			itemDef.modelID = 2429;
			itemDef.name = "Gold Ring";
			itemDef.actions = new String[] { null, null, null, null, null, null };
			itemDef.modelZoom = 760;
			itemDef.rotationX = 28;
			itemDef.rotationY = 552;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 15009;
			itemDef.certTemplateID = 799;
			break;

		case 15262:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.actions[2] = "Open-All";
			break;
		case 6570:
			itemDef.actions[2] = "Upgrade";
			break;
		case 4155:
			itemDef.name = "Slayer gem";
			itemDef.actions = new String[] { "Activate", null, "Social-Slayer", null, "Destroy" };
			break;
		case 13663:
			itemDef.name = "Stat reset cert.";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Open";
			break;
		case 292:
			itemDef.name = "Ingredients book";
			break;
		case 19111:
			itemDef.name = "TokHaar-Kal";
			itemDef.value = 60000;
			itemDef.maleEquip1 = 62575;
			itemDef.maleEquip1 = 62582;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 62592;
			itemDef.description = "A cape made of ancient, enchanted rocks.";
			itemDef.modelZoom = 2086;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffsetX = 0;
			itemDef.rotationY = 533;
			itemDef.rotationX = 333;
			break;
		case 989:
			itemDef.modelID = 2372;
			itemDef.name = "Mystery key III";
			itemDef.description = "Use it on the mystery chest.";
			itemDef.modelZoom = 700;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 20;
			itemDef.rotationY = 328;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 302770 };
			itemDef.editedModelColor = new int[] { 8128 };
			itemDef.stackable = true;
			itemDef.colourRedefine3 = 6000;

			break;
			case 17000:
				itemDef.modelID = 2372;
				itemDef.name = "Mystery key I";
				itemDef.description = "Use it on the mystery chest.";
				itemDef.modelZoom = 700;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = -3;
				itemDef.rotationX = 20;
				itemDef.rotationY = 328;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				itemDef.colourRedefine3 = 220000;
				itemDef.stackable = true;
				break;
			case 17001:
				itemDef.modelID = 2372;
				itemDef.name = "Mystery key II";
				itemDef.description = "Use it on the mystery chest.";
				itemDef.modelZoom = 700;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = -3;
				itemDef.rotationX = 20;
				itemDef.rotationY = 328;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				itemDef.colourRedefine3 = 180000;
				itemDef.stackable = true;
				break;

			case 17003:
				itemDef.modelID = 2372;
				itemDef.name = "Mystery key IIII";
				itemDef.description = "Use it on the mystery chest.";
				itemDef.modelZoom = 700;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = -3;
				itemDef.rotationX = 20;
				itemDef.rotationY = 328;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				itemDef.colourRedefine3 = 898;
				itemDef.stackable = true;
				break;

			case 995:
			itemDef.name = "Coins";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[3] = "Add-to-pouch";
			itemDef.colourRedefine = 800;
			break;


		case 11609:
			itemDef.modelID = 65211;
			itemDef.name = "Bullets";
			itemDef.description = "Bullets";
			itemDef.modelZoom = 720;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 117;
			itemDef.rotationY = 477;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = true;
			break;
		case 20692:
			itemDef.modelID = 3683;
			itemDef.name = "Bullet box";
			itemDef.description = "It's a bullet box with 1k bullets in it!";
			itemDef.modelZoom = 3840;
			itemDef.modelOffsetY = -126;
			itemDef.modelOffset1 = 15;
			itemDef.rotationX = 1684;
			itemDef.rotationY = 108;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Open", null, null, null, "Drop" };
			break;
		case 17291:
			itemDef.name = "Blood necklace";
			itemDef.actions = new String[] { null, "Wear", null, null, null, null };
			break;
		case 20084:
			itemDef.name = "Golden Maul";
			break;
		case 6199:
			itemDef.modelID = 2426;
			itemDef.name = "Mystery Box";
			itemDef.description = "Mystery box";
			itemDef.modelZoom = 1180;
			itemDef.modelOffsetY = -14;
			itemDef.rotationX = 172;
			itemDef.rotationY = 160;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Open", null, null, null, null };
//			itemDef.newModelColor = new int[] { 36783, 39839, 39855 };
//			itemDef.editedModelColor = new int[] { 22410, 926, 2999 };
            itemDef.colourRedefine2 = 36783;

            itemDef.stackable = true;
			break;
		case 15501:
			itemDef.modelID = 2426;
			itemDef.name = "Mega Mystery Box";
			itemDef.description = "Mega Mystery box";
			itemDef.modelZoom = 1180;
			itemDef.modelOffsetY = -14;
			itemDef.rotationX = 172;
			itemDef.rotationY = 160;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Open", null, null, null, null };
			itemDef.newModelColor = new int[] { 12, 6, 63 };
			itemDef.editedModelColor = new int[] { 22410, 926, 2999 };
   //         itemDef.colourRedefine2 = 12;
			itemDef.stackable = true;
			break;
		case 15505:
			itemDef.modelID = 2426;
			itemDef.name = "Pet Mystery Box";
			itemDef.description = "Mega Mystery box";
			itemDef.modelZoom = 1180;
			itemDef.modelOffsetY = -14;
			itemDef.rotationX = 172;
			itemDef.rotationY = 160;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Open", null, null, null, null };
			itemDef.newModelColor = new int[] { 5000, 6, 243 };
			itemDef.editedModelColor = new int[] { 6000, 926, 2999 };
			itemDef.stackable = true;
			break;
		case 996:
		case 997:
		case 998:
		case 999:
		case 1000:
		case 1001:
		case 1002:
		case 1003:
		case 1004:
			itemDef.name = "Coins";
			itemDef.colourRedefine = 800;
			break;
		case 14022:
			itemDef.modelID = 65270;
			itemDef.modelID = 65270;
			itemDef.name = "Completionist Cape";
			itemDef.description = "We'd pat you on the back, but this cape would get in the way.";
			itemDef.modelZoom = 1385;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 24;
			itemDef.rotationY = 279;
			itemDef.rotationX = 948;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65297;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customize";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214; // red
			itemDef.editedModelColor[1] = 65200; // darker red
			itemDef.editedModelColor[2] = 65186; // dark red
			itemDef.editedModelColor[3] = 62995; // darker red
			itemDef.newModelColor[0] = 65214;// cape
			itemDef.newModelColor[1] = 65200;// cape
			itemDef.newModelColor[2] = 65186;// outline
			itemDef.newModelColor[3] = 62995;// cape
			break;
		case 14018:
			itemDef.modelID = 65203;
			itemDef.name = "Ornate katana";
			itemDef.modelZoom = 1350;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = -25;
			itemDef.modelOffsetY = 0;
			itemDef.value = 50000;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 5324;
			itemDef.femaleEquip1 = 5324;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.actions[4] = "Destroy";
			break;
		case 20250:
			itemDef.modelID = 55057;
			itemDef.name = "@red@Amer@whi@ican @blu@Whip";
			itemDef.description = "It's a american whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 55058;
			itemDef.femaleEquip1 = 55058;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 20251:
			itemDef.modelID = 55055;
			itemDef.name = "@red@American @whi@Torva @blu@platelegs";
			itemDef.description = "It's a set of american torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 55056;
			itemDef.femaleEquip1 = 55056;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 20252:
			itemDef.modelID = 55053;
			itemDef.name = "@red@American @whi@Torva @blu@Fullhelm";
			itemDef.description = "It's a american torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 55054;
			itemDef.femaleEquip1 = 55054;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 20253:
			itemDef.modelID = 55051;
			itemDef.name = "@red@American @whi@Torva @blu@Platebody";
			itemDef.description = "It's a american torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 55052;
			itemDef.femaleEquip1 = 55052;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14556:
			itemDef.name = "@red@Amer@whi@ican @blu@Gloves";
			itemDef.description = "It's a pair of american gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65532;
			itemDef.maleEquip1 = 65533;
			itemDef.femaleEquip1 = 65533;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14557:
			itemDef.name = "@red@Amer@whi@ican @blu@Boots";
			itemDef.description = "It's a pair of american boots.";
			itemDef.modelID = 65531;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65530;
			itemDef.femaleEquip1 = 65530;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14558:
			itemDef.modelID = 65534;
			itemDef.name = "@red@Amer@whi@ican @blu@Wings";
			itemDef.description = "It's a pair of american wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65394;
			itemDef.femaleEquip1 = 65394;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14559:
			itemDef.modelID = 15038;
			itemDef.name = "@bla@Oreo @whi@Whip";
			itemDef.description = "It's a oreo whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 15039;
			itemDef.femaleEquip1 = 15039;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14560:
			itemDef.modelID = 15036;
			itemDef.name = "@bla@Oreo @whi@Torva @bla@Platelegs";
			itemDef.description = "It's a set of oreo torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 15037;
			itemDef.femaleEquip1 = 15037;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14561:
			itemDef.modelID = 15034;
			itemDef.name = "@bla@Oreo @whi@Torva @bla@Fullhelm";
			itemDef.description = "It's a oreo torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 15035;
			itemDef.femaleEquip1 = 15035;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14562:
			itemDef.modelID = 15028;
			itemDef.name = "@bla@Oreo @whi@Torva @bla@Platebody";
			itemDef.description = "It's a oreo torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 15029;
			itemDef.femaleEquip1 = 15029;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14563:
			itemDef.name = "@bla@Oreo @whi@Gloves";
			itemDef.description = "It's a pair of oreo gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 15033;
			itemDef.maleEquip1 = 15032;
			itemDef.femaleEquip1 = 15032;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14564:
			itemDef.name = "@bla@Oreo @whi@Boots";
			itemDef.description = "It's a pair of oreo boots.";
			itemDef.modelID = 15031;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 15030;
			itemDef.femaleEquip1 = 15030;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14565:
			itemDef.modelID = 15041;
			itemDef.name = "@bla@Oreo @whi@Wings";
			itemDef.description = "It's a pair of oreo wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 15040;
			itemDef.femaleEquip1 = 15040;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14566:
			itemDef.modelID = 15024;
			itemDef.name = "@whi@Sky @blu@Whip";
			itemDef.description = "It's a sky whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 15025;
			itemDef.femaleEquip1 = 15025;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14567:
			itemDef.modelID = 15022;
			itemDef.name = "@whi@Sky @blu@Torva @whi@BootsPlatelegs";
			itemDef.description = "It's a set of sky torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 15023;
			itemDef.femaleEquip1 = 15023;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14568:
			itemDef.modelID = 15020;
			itemDef.name = "@whi@Sky @blu@Torva @whi@Fullhelm";
			itemDef.description = "It's a sky torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 15021;
			itemDef.femaleEquip1 = 15021;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14569:
			itemDef.modelID = 15014;
			itemDef.name = "@whi@Sky @blu@Torva @whi@Platebody";
			itemDef.description = "It's a sky torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 15015;
			itemDef.femaleEquip1 = 15015;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14570:
			itemDef.name = "@whi@Sky @blu@Gloves";
			itemDef.description = "It's a pair of sky gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 15019;
			itemDef.maleEquip1 = 15018;
			itemDef.femaleEquip1 = 15018;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14571:
			itemDef.name = "@whi@Sky @blu@Boots";
			itemDef.description = "It's a pair of sky boots.";
			itemDef.modelID = 15017;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 15016;
			itemDef.femaleEquip1 = 15016;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14572:
			itemDef.modelID = 15027;
			itemDef.name = "@whi@Sky @blu@Wings";
			itemDef.description = "It's a pair of sky wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 15026;
			itemDef.femaleEquip1 = 15026;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 20690:
			itemDef.modelID = 65526;
			itemDef.name = "@red@ Darth @bla@Maul @red@Whip";
			itemDef.description = "It's a darth maul whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65527;
			itemDef.femaleEquip1 = 65527;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14581:
			itemDef.modelID = 65524;
			itemDef.name = "@red@ Darth @bla@Maul @red@Torva @bla@Platelegs";
			itemDef.description = "It's a set of darth maul torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 65525;
			itemDef.femaleEquip1 = 65525;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14582:
			itemDef.modelID = 65522;
			itemDef.name = "@red@ Darth @bla@Maul @red@Torva @bla@Fullhelm";
			itemDef.description = "It's a darth maul torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 65523;
			itemDef.femaleEquip1 = 65523;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14583:
			itemDef.modelID = 65516;
			itemDef.name = "@red@ Darth @bla@Maul @red@Torva @bla@Platebody";
			itemDef.description = "It's a darth maul torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 65517;
			itemDef.femaleEquip1 = 65517;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14584:
			itemDef.name = "@red@ Darth @bla@Maul @red@Gloves";
			itemDef.description = "It's a pair of darth maul gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65521;
			itemDef.maleEquip1 = 65520;
			itemDef.femaleEquip1 = 65520;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14585:
			itemDef.name = "@red@ Darth @bla@Maul @red@Boots";
			itemDef.description = "It's a pair of darth maul boots.";
			itemDef.modelID = 65519;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65518;
			itemDef.femaleEquip1 = 65518;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14586:
			itemDef.modelID = 65529;
			itemDef.name = "@red@ Darth @bla@Maul @red@Wings";
			itemDef.description = "It's a pair of darth maul wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65528;
			itemDef.femaleEquip1 = 65528;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14587:
			itemDef.modelID = 110;
			itemDef.name = "@gre@Cash Whip";
			itemDef.description = "It's a cash whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 111;
			itemDef.femaleEquip1 = 111;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14588:
			itemDef.modelID = 1736;
			itemDef.name = "@gre@Cash Torva Platelegs";
			itemDef.description = "It's a set of cash torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 113;
			itemDef.femaleEquip1 = 113;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14589:
			itemDef.modelID = 114;
			itemDef.name = "@gre@Cash Torva Fullhelm";
			itemDef.description = "It's a cash torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 115;
			itemDef.femaleEquip1 = 115;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 14590:
			itemDef.modelID = 116;
			itemDef.name = "@gre@Cash Torva Platebody";
			itemDef.description = "It's a cash torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 117;
			itemDef.femaleEquip1 = 117;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14591:
			itemDef.name = "@gre@Cash Gloves";
			itemDef.description = "It's a pair of cash gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 120;
			itemDef.maleEquip1 = 121;
			itemDef.femaleEquip1 = 121;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 79:
			itemDef.name = "@gre@Cash Boots";
			itemDef.description = "It's a pair of cash boots.";
			itemDef.modelID = 122;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 123;
			itemDef.femaleEquip1 = 123;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 80:
			itemDef.modelID = 118;
			itemDef.name = "@gre@Cash Wings";
			itemDef.description = "It's a pair of cash wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 119;
			itemDef.femaleEquip1 = 119;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 81:
			itemDef.modelID = 55071;
			itemDef.name = "@bla@Silver Whip";
			itemDef.description = "It's a silver whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 55072;
			itemDef.femaleEquip1 = 55072;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14619:
			itemDef.modelID = 55049;
			itemDef.name = "@bla@Silver Torva Platelegs";
			itemDef.description = "It's a set of silver torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 55050;
			itemDef.femaleEquip1 = 55050;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14596:
			itemDef.modelID = 55048;
			itemDef.name = "@bla@Silver Torva Fullhelm";
			itemDef.description = "It's a silver torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 55047;
			itemDef.femaleEquip1 = 55047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14597:
			itemDef.modelID = 55045;
			itemDef.name = "@bla@Silver Torva Platebody";
			itemDef.description = "It's a silver torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 55046;
			itemDef.femaleEquip1 = 55046;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14598:
			itemDef.name = "@bla@Silver Gloves";
			itemDef.description = "It's a pair of silver gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65513;
			itemDef.maleEquip1 = 65512;
			itemDef.femaleEquip1 = 65512;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14599:
			itemDef.name = "@bla@Silver Boots";
			itemDef.description = "It's a pair of silver boots.";
			itemDef.modelID = 65511;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65514;
			itemDef.femaleEquip1 = 65514;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 18742:
			itemDef.modelID = 55074;
			itemDef.name = "@bla@Silver Wings";
			itemDef.description = "It's a pair of silver wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 55073;
			itemDef.femaleEquip1 = 55073;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 82:
			itemDef.modelID = 65507;
			itemDef.name = "@blu@Water Whip";
			itemDef.description = "It's a water whip.";
			itemDef.modelZoom = 987;
			itemDef.rotationY = 440;
			itemDef.rotationX = 630;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -1;
			itemDef.maleEquip1 = 65506;
			itemDef.femaleEquip1 = 65506;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 894:
			itemDef.modelID = 65509;
			itemDef.name = "@whi@Air Whip";
			itemDef.description = "It's a air whip.";
			itemDef.modelZoom = 1468;
			itemDef.rotationY = 504;
			itemDef.rotationX = 200;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -1;
			itemDef.maleEquip1 = 65510;
			itemDef.femaleEquip1 = 65510;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 895:
			itemDef.modelID = 65508;
			itemDef.name = "@gre@ Earth Whip";
			itemDef.description = "It's a earth whip.";
			itemDef.modelZoom = 840;
			itemDef.rotationY = 280;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -1;
			itemDef.maleEquip1 = 65503;
			itemDef.femaleEquip1 = 65503;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 896:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 32703;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Cyan santa hat";
			itemDef.description = "It's a cyan santa hat.";
			break;
		case 924:
			itemDef.modelID = 65490;
			itemDef.name = "Rainbow santa hat";
			itemDef.description = "Santa hat";
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65489;
			itemDef.femaleEquip1 = 65488;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 2380:
			itemDef.modelID = 65487;
			itemDef.name = "Striped santa hat";
			itemDef.description = "Santa hat";
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65486;
			itemDef.femaleEquip1 = 65485;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 909:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 5055;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Orange santa hat";
			itemDef.description = "It's a orange santa hat.";
			break;
		case 898:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 43967;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Blue santa hat";
			itemDef.description = "It's a blue santa hat.";
			break;
		case 899:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 54207;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Pink santa hat";
			itemDef.description = "It's a pink santa hat.";
			break;
		case 900:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 11199;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Yellow santa hat";
			itemDef.description = "It's a yellow santa hat.";
			break;
		case 901:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 22425;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Green santa hat";
			itemDef.description = "It's a green santa hat.";
			break;
		case 902:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 22463;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Lime santa hat";
			itemDef.description = "It's a lime santa hat.";
			break;
		case 903:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 52127;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Purple santa hat";
			itemDef.description = "It's a purple santa hat.";
			break;
		case 2548:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 52127;
			itemDef.modelID = 65465;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65466;
			itemDef.femaleEquip1 = 65466;
			itemDef.name = "Camo partyhat";
			itemDef.description = "It's a camo partyhat.";
			break;
		case 2547:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 52127;
			itemDef.modelID = 65463;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65464;
			itemDef.femaleEquip1 = 65464;
			itemDef.name = "Camo santa hat";
			itemDef.description = "It's a camo santa hat.";
			break;
		case 904:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 52127;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Purple partyhat";
			itemDef.description = "It's a purple partyhat.";
			break;
		case 3088:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 5055;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Orange partyhat";
			itemDef.description = "It's a orange partyhat.";
			break;
		case 906:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 22463;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Lime partyhat";
			itemDef.description = "It's a lime partyhat.";
			break;
		case 907:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 54207;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Pink partyhat";
			itemDef.description = "It's a pink partyhat.";
			break;
		case 908:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 32703;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Cyan partyhat";
			itemDef.description = "It's a cyan partyhat.";
			break;
		case 926:
			itemDef.modelID = 65481;
			itemDef.name = "Rainbow partyhat";
			itemDef.description = "Rainbow partyhat";
			itemDef.modelZoom = 440;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1845;
			itemDef.rotationY = 121;
			itemDef.maleEquip1 = 65480;
			itemDef.femaleEquip1 = 65479;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 3878:
			itemDef.modelID = 65484;
			itemDef.name = "Striped partyhat";
			itemDef.description = "Striped partyhat";
			itemDef.modelZoom = 440;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1845;
			itemDef.rotationY = 121;
			itemDef.maleEquip1 = 65483;
			itemDef.femaleEquip1 = 65482;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 910:
			itemDef.modelID = 40920;
			itemDef.name = "Mystical Spirit Shield";
			itemDef.description = "It's a mystical spirit shield";
			itemDef.newModelColor = new int[] { 32703, 33727, 34751, 35775, 36799, 37823, 38847, 39871, 43967, 40895,
					41919, 42943 };
			itemDef.editedModelColor = new int[] { 44635, 44612, 44606, 44615, 44641, 44564, 44575, 44618, 105, 44603,
					44570, 4500 };
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 911:
			itemDef.modelID = 40920;
			itemDef.name = "Deathly Spirit Shield";
			itemDef.description = "It's a deathly spirit shield";
			itemDef.editedModelColor = new int[13];
			itemDef.newModelColor = new int[13];
			itemDef.editedModelColor[0] = 44635;
			itemDef.newModelColor[0] = 959;
			itemDef.editedModelColor[1] = 44612;
			itemDef.newModelColor[1] = 1983;
			itemDef.editedModelColor[2] = 44606;
			itemDef.newModelColor[2] = 3007;
			itemDef.editedModelColor[3] = 44615;
			itemDef.newModelColor[3] = 4031;
			itemDef.editedModelColor[4] = 44641;
			itemDef.newModelColor[4] = 5055;
			itemDef.editedModelColor[5] = 44564;
			itemDef.newModelColor[5] = 6079;
			itemDef.editedModelColor[6] = 44575;
			itemDef.newModelColor[6] = 7103;
			itemDef.editedModelColor[7] = 44618;
			itemDef.newModelColor[7] = 8127;
			itemDef.editedModelColor[8] = 105;
			itemDef.newModelColor[8] = 0;
			itemDef.editedModelColor[9] = 44603;
			itemDef.newModelColor[9] = 9151;
			itemDef.editedModelColor[10] = 44570;
			itemDef.newModelColor[10] = 11199;
			itemDef.editedModelColor[11] = 4500;
			itemDef.newModelColor[11] = 12223;
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 912:
			itemDef.modelID = 40920;
			itemDef.name = "Lime Spirit Shield";
			itemDef.description = "It's a lime spirit shield";
			itemDef.editedModelColor = new int[13];
			itemDef.newModelColor = new int[13];
			itemDef.editedModelColor[0] = 44635;
			itemDef.newModelColor[0] = 22463;
			itemDef.editedModelColor[1] = 44612;
			itemDef.newModelColor[1] = 22463;
			itemDef.editedModelColor[2] = 44606;
			itemDef.newModelColor[2] = 22463;
			itemDef.editedModelColor[3] = 44615;
			itemDef.newModelColor[3] = 22463;
			itemDef.editedModelColor[4] = 44641;
			itemDef.newModelColor[4] = 22463;
			itemDef.editedModelColor[5] = 44564;
			itemDef.newModelColor[5] = 22463;
			itemDef.editedModelColor[6] = 44575;
			itemDef.newModelColor[6] = 22463;
			itemDef.editedModelColor[7] = 44618;
			itemDef.newModelColor[7] = 22463;
			itemDef.editedModelColor[8] = 105;
			itemDef.newModelColor[8] = 127;
			itemDef.editedModelColor[9] = 44603;
			itemDef.newModelColor[9] = 22463;
			itemDef.editedModelColor[10] = 44570;
			itemDef.newModelColor[10] = 22463;
			itemDef.editedModelColor[11] = 4500;
			itemDef.newModelColor[11] = 22463;
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3091:
			itemDef.modelID = 40920;
			itemDef.name = "Pink Spirit Shield";
			itemDef.description = "It's a pink spirit shield";
			itemDef.editedModelColor = new int[13];
			itemDef.newModelColor = new int[13];
			itemDef.editedModelColor[0] = 44635;
			itemDef.newModelColor[0] = 54207;
			itemDef.editedModelColor[1] = 44612;
			itemDef.newModelColor[1] = 54207;
			itemDef.editedModelColor[2] = 44606;
			itemDef.newModelColor[2] = 54207;
			itemDef.editedModelColor[3] = 44615;
			itemDef.newModelColor[3] = 54207;
			itemDef.editedModelColor[4] = 44641;
			itemDef.newModelColor[4] = 54207;
			itemDef.editedModelColor[5] = 44564;
			itemDef.newModelColor[5] = 54207;
			itemDef.editedModelColor[6] = 44575;
			itemDef.newModelColor[6] = 54207;
			itemDef.editedModelColor[7] = 44618;
			itemDef.newModelColor[7] = 54207;
			itemDef.editedModelColor[8] = 105;
			itemDef.newModelColor[8] = 127;
			itemDef.editedModelColor[9] = 44603;
			itemDef.newModelColor[9] = 54207;
			itemDef.editedModelColor[10] = 44570;
			itemDef.newModelColor[10] = 54207;
			itemDef.editedModelColor[11] = 4500;
			itemDef.newModelColor[11] = 54207;
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 914:
			itemDef.modelID = 40920;
			itemDef.name = "Green Spirit Shield";
			itemDef.description = "It's a green spirit shield";
			itemDef.editedModelColor = new int[13];
			itemDef.newModelColor = new int[13];
			itemDef.editedModelColor[0] = 44635;
			itemDef.newModelColor[0] = 22425;
			itemDef.editedModelColor[1] = 44612;
			itemDef.newModelColor[1] = 22425;
			itemDef.editedModelColor[2] = 44606;
			itemDef.newModelColor[2] = 22425;
			itemDef.editedModelColor[3] = 44615;
			itemDef.newModelColor[3] = 22425;
			itemDef.editedModelColor[4] = 44641;
			itemDef.newModelColor[4] = 22425;
			itemDef.editedModelColor[5] = 44564;
			itemDef.newModelColor[5] = 22425;
			itemDef.editedModelColor[6] = 44575;
			itemDef.newModelColor[6] = 22425;
			itemDef.editedModelColor[7] = 44618;
			itemDef.newModelColor[7] = 22425;
			itemDef.editedModelColor[8] = 105;
			itemDef.newModelColor[8] = 127;
			itemDef.editedModelColor[9] = 44603;
			itemDef.newModelColor[9] = 22425;
			itemDef.editedModelColor[10] = 44570;
			itemDef.newModelColor[10] = 22425;
			itemDef.editedModelColor[11] = 4500;
			itemDef.newModelColor[11] = 22425;
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3089:
			itemDef.modelID = 40920;
			itemDef.name = "Purple Spirit Shield";
			itemDef.description = "It's a purple spirit shield";
			itemDef.editedModelColor = new int[13];
			itemDef.newModelColor = new int[13];
			itemDef.editedModelColor[0] = 44635;
			itemDef.newModelColor[0] = 52127;
			itemDef.editedModelColor[1] = 44612;
			itemDef.newModelColor[1] = 52127;
			itemDef.editedModelColor[2] = 44606;
			itemDef.newModelColor[2] = 52127;
			itemDef.editedModelColor[3] = 44615;
			itemDef.newModelColor[3] = 52127;
			itemDef.editedModelColor[4] = 44641;
			itemDef.newModelColor[4] = 52127;
			itemDef.editedModelColor[5] = 44564;
			itemDef.newModelColor[5] = 52127;
			itemDef.editedModelColor[6] = 44575;
			itemDef.newModelColor[6] = 52127;
			itemDef.editedModelColor[7] = 44618;
			itemDef.newModelColor[7] = 52127;
			itemDef.editedModelColor[8] = 105;
			itemDef.newModelColor[8] = 127;
			itemDef.editedModelColor[9] = 44603;
			itemDef.newModelColor[9] = 52127;
			itemDef.editedModelColor[10] = 44570;
			itemDef.newModelColor[10] = 52127;
			itemDef.editedModelColor[11] = 4500;
			itemDef.newModelColor[11] = 52127;
			itemDef.modelZoom = 1616;
			itemDef.rotationY = 396;
			itemDef.rotationX = 1050;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleEquip1 = 40940;
			itemDef.femaleEquip1 = 40940;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 20259:
			itemDef.modelID = 2674;
			itemDef.name = "Purgatory Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 302770 };
			itemDef.editedModelColor = new int[] { 127 };
			break;
		case 20260:
			itemDef.modelID = 65326;
			itemDef.name = "Darth Maul Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 20256:
			itemDef.modelID = 3419;
			itemDef.name = "Charmeleon Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 11199 };
			itemDef.editedModelColor = new int[] { 127 };
			break;
		case 20249:
			itemDef.modelID = 2674;
			itemDef.name = "Silver Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 50 };
			itemDef.editedModelColor = new int[] { 127 };
			break;
		case 20254:
			itemDef.modelID = 65327;
			itemDef.name = "Cash Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 20255:
			itemDef.modelID = 65325;
			itemDef.name = "Sky Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
            case 3879:
                itemDef.modelID = 2674;
                itemDef.name = "Drygore God Bones";
                itemDef.description = "Bones";
                itemDef.modelZoom = 1410;
                itemDef.modelOffset1 = 2;
                itemDef.rotationX = 368;
                itemDef.rotationY = 400;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
                itemDef.newModelColor = new int[] { 6000 };
                itemDef.editedModelColor = new int[] { 127 };
                break;
		case 1187:
			itemDef.modelID = 2840;
			itemDef.name = "Blue Dragon sq shield";
			itemDef.description = "Dragon sq shield";
			itemDef.modelZoom = 1730;
			itemDef.modelOffsetY = 10;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 120;
			itemDef.rotationY = 352;
			itemDef.maleEquip1 = 565;
			itemDef.femaleEquip1 = 565;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1, 60, 43803, 60 };
			itemDef.editedModelColor = new int[] { 0, 37, 924, 7054 };
			break;
		case 5698:
			itemDef.modelID = 2718;
			itemDef.name = "Blue Dragon dagger (P)";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 539;
			itemDef.femaleEquip1 = 539;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803 };
			itemDef.editedModelColor = new int[] { 924 };
			break;
		case 18000:
			itemDef.modelID = 31296;
			itemDef.name = "Dragonic Katana";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 1700;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 31295;
			itemDef.femaleEquip1 = 31295;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 18001:
			itemDef.modelID = 31298;
			itemDef.name = "Anouke's Glaive";
			itemDef.modelZoom = 1500;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.colourRedefine2 = 548374;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 31297;
			itemDef.femaleEquip1 = 31297;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
			case 20000:
				itemDef.modelID = 31298;
				itemDef.name = "Anouke's Glaive";
				itemDef.modelZoom = 1500;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.colourRedefine2 = 84736;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 31297;
				itemDef.femaleEquip1 = 31297;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
		case 18002:
			itemDef.modelID = 31298;
			itemDef.name = "Anouke's Offhand";
			itemDef.modelZoom = 1400;
			itemDef.colourRedefine2 = 548374;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1274;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 31299;
			itemDef.femaleEquip1 = 31299;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;

		case 18003:
			itemDef.modelID = 33000;
			itemDef.colourRedefine2 = 548374;
			itemDef.name = "Anouke Mask";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 760;
			itemDef.rotationY = 135;
			itemDef.rotationX = 148;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 33001;
			itemDef.femaleEquip1 = 33001;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 18004:
			itemDef.modelID = 33002;
			itemDef.colourRedefine2 = 548374;
			itemDef.name = "Anouke Body";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 1050;
			itemDef.rotationX = 2041;
			itemDef.rotationY = 593;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -11;
			itemDef.maleEquip1 = 33003;
			itemDef.femaleEquip1 = 33003;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;

		case 18005:
			itemDef.modelID = 33005;
			itemDef.colourRedefine2 = 548374;
			itemDef.name = "Anouke Legs";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 1250;
			itemDef.rotationY = 200;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 16;
			itemDef.maleEquip1 = 33005;
			itemDef.femaleEquip1 = 33005;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;

		case 18006:
			itemDef.modelID = 33007;
			itemDef.colourRedefine2 = 548374;
			itemDef.name = "Anouke Boots";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 770;
			itemDef.rotationY = 188;
			itemDef.rotationX = 1988;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -4;
			itemDef.maleEquip1 = 33007;
			itemDef.femaleEquip1 = 33007;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 18007:
			itemDef.modelID = 33008;
			itemDef.colourRedefine2 = 548374;
			itemDef.name = "Anouke Gloves";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 33009;
			itemDef.femaleEquip1 = 33009;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 18008:
			itemDef.modelID = 33011;
			itemDef.name = "Z sword";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 33010;
			itemDef.femaleEquip1 = 33010;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 18009:
			itemDef.modelID = 33016;
			itemDef.name = "Charged Scythe";
			itemDef.description = "Dragon dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 33015;
			itemDef.femaleEquip1 = 33015;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
			case 18010:
				itemDef.modelID = 35644;
				itemDef.name = "Charged Scythe";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 760;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 35644;
				itemDef.femaleEquip1 = 35644;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 18011:
				itemDef.modelID = 33017;
				itemDef.name = "Extase Sword";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 760;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 33018;
				itemDef.femaleEquip1 = 33018;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19000:
			    itemDef.modelID = 60000;
				itemDef.colourRedefine3 = 50;
				itemDef.name = "Dagganoth Slayer helm";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 750;
				itemDef.rotationY = 135;
				itemDef.rotationX = 148;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.maleEquip1 = 60001;
				itemDef.femaleEquip1 = 60001;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;

            case 20005:
                itemDef.modelID = 31232;
                itemDef.name = "@blk@ Ichigo's Katana";
                itemDef.description = "It's a Katana!";
                itemDef.modelZoom = 1519;
                itemDef.rotationY = 595;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 31233;
                itemDef.femaleEquip1 = 31233;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                break;

			case 19001:
				itemDef.modelID = 60002;
				itemDef.colourRedefine3 = 50;
				itemDef.name = "Dagganoth Slayer platebody";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 1200;
				itemDef.rotationX = 2041;
				itemDef.rotationY = 593;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.maleEquip1 = 60003;
				itemDef.femaleEquip1 = 60003;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19002:
				itemDef.modelID = 60004;
				itemDef.colourRedefine3 = 50;
				itemDef.name = "Dagganoth Slayer platelegs";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 1200;
				itemDef.rotationX = 0;
				itemDef.rotationY = 360;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.maleEquip1 = 60005;
				itemDef.femaleEquip1 = 60005;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19003:
				itemDef.modelID = 60006;
				itemDef.colourRedefine3 = 50;

				itemDef.name = "Dagganoth Slayer boots";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 760;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 60006;
				itemDef.femaleEquip1 = 60006;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19004:
				itemDef.modelID = 60007;
				itemDef.colourRedefine3 = 50;

				itemDef.name = "Dagganoth Slayer wings";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 1200;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 60008;
				itemDef.femaleEquip1 = 60008;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19005:
				itemDef.modelID = 60009;
				itemDef.colourRedefine3 = 50;
				itemDef.name = "Dagganoth Slayer blade";
				itemDef.description = "Dragon dagger";
				itemDef.modelZoom = 760;
				itemDef.modelOffsetY = -4;
				itemDef.rotationX = 1276;
				itemDef.rotationY = 472;
				itemDef.maleEquip1 = 60010;
				itemDef.femaleEquip1 = 60010;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 19006:
				itemDef.modelID = 47349;
				itemDef.colourRedefine3 = 50;
				itemDef.name = "Dagganoth Slayer shield";
				itemDef.description = "kiteshield";
				itemDef.modelZoom = 1450;
				itemDef.modelOffsetY = -14;
				itemDef.modelOffset1 = -6;
				itemDef.rotationX = 1825;
				itemDef.rotationY = 344;
				itemDef.maleEquip1 = 17120;
				itemDef.femaleEquip1 = 17120;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;


			case 1249:
			itemDef.modelID = 2482;
			itemDef.name = "Blue Dragon spear";
			itemDef.description = "Dragon spear";
			itemDef.modelZoom = 1490;
			itemDef.modelOffsetX = 144;
			itemDef.modelOffsetY = -4;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 112;
			itemDef.rotationY = 1960;
			itemDef.maleEquip1 = 505;
			itemDef.femaleEquip1 = 505;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803 };
			itemDef.editedModelColor = new int[] { 924 };
			break;
		case 1377:
			itemDef.modelID = 2801;
			itemDef.name = "Blue Dragon battleaxe";
			itemDef.description = "Dragon battleaxe";
			itemDef.modelZoom = 1360;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 2020;
			itemDef.rotationY = 420;
			itemDef.maleEquip1 = 559;
			itemDef.femaleEquip1 = 559;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803 };
			itemDef.editedModelColor = new int[] { 924 };
			break;
		case 1434:
			itemDef.modelID = 2391;
			itemDef.name = "Blue Dragon mace";
			itemDef.description = "Dragon mace";
			itemDef.modelZoom = 1440;
			itemDef.modelOffsetY = 13;
			itemDef.modelOffset1 = -13;
			itemDef.rotationX = 1388;
			itemDef.rotationY = 348;
			itemDef.maleEquip1 = 493;
			itemDef.femaleEquip1 = 493;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803 };
			itemDef.editedModelColor = new int[] { 924 };
			break;
		case 3204:
			itemDef.modelID = 3886;
			itemDef.name = "Blue Dragon halberd";
			itemDef.description = "Dragon halberd";
			itemDef.modelZoom = 2160;
			itemDef.modelOffsetY = 12;
			itemDef.modelOffset1 = -1;
			itemDef.rotationY = 364;
			itemDef.maleEquip1 = 3885;
			itemDef.femaleEquip1 = 3885;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803, 43797, 43793 };
			itemDef.editedModelColor = new int[] { 924, 918, 914 };
			break;
		case 6739:
			itemDef.modelID = 9959;
			itemDef.name = "Blue Dragon hatchet";
			itemDef.description = "Dragon hatchet";
			itemDef.modelZoom = 1450;
			itemDef.modelOffsetY = 6;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1288;
			itemDef.rotationY = 520;
			itemDef.maleEquip1 = 9957;
			itemDef.femaleEquip1 = 9957;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1, 49, 43803, 43450 };
			itemDef.editedModelColor = new int[] { 0, 37, 924, 1467 };
			break;
		case 11732:
			itemDef.modelID = 28138;
			itemDef.name = "Blue Dragon boots";
			itemDef.description = "Dragon boots";
			itemDef.modelZoom = 595;
			itemDef.modelOffsetY = -1;
			itemDef.rotationY = 96;
			itemDef.maleEquip1 = 27738;
			itemDef.femaleEquip1 = 27754;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43805, 43791 };
			itemDef.editedModelColor = new int[] { 926, 912 };
			break;
		case 4087:
			itemDef.modelID = 5026;
			itemDef.name = "Blue Dragon platelegs";
			itemDef.description = "Dragon platelegs";
			itemDef.modelZoom = 1740;
			itemDef.modelOffsetY = -8;
			itemDef.rotationY = 444;
			itemDef.maleEquip1 = 5024;
			itemDef.femaleEquip1 = 5025;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43787, 43805, 43791 };
			itemDef.editedModelColor = new int[] { 908, 926, 912 };
			break;
		case 13262:
			itemDef.modelID = 15335;
			itemDef.name = "Blue dragon defender";
			itemDef.description = "blue dragon defender";
			itemDef.modelZoom = 490;
			itemDef.modelOffsetY = -30;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 1882;
			itemDef.rotationY = 221;
			itemDef.maleEquip1 = 15413;
			itemDef.femaleEquip1 = 15413;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43805, 43805 };
			itemDef.editedModelColor = new int[] { 28, 74 };
			break;
		case 14484:
			itemDef.modelID = 44590;
			itemDef.name = "Blue Dragon claws";
			itemDef.description = "Blue Dragon claws";
			itemDef.modelZoom = 789;
			itemDef.modelOffsetY = -23;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 60;
			itemDef.rotationY = 240;
			itemDef.maleEquip1 = 43660;
			itemDef.femaleEquip1 = 43651;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43793, 43813, 43808, 43801, 43797 };
			itemDef.editedModelColor = new int[] { 914, 934, 929, 922, 918 };
			break;
		case 8850:
			itemDef.modelID = 15335;
			itemDef.name = "Morune defender";
			itemDef.description = "morune defender";
			itemDef.modelZoom = 490;
			itemDef.modelOffsetY = -30;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 1882;
			itemDef.rotationY = 221;
			itemDef.maleEquip1 = 15413;
			itemDef.femaleEquip1 = 15413;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 890, 928 };
			itemDef.editedModelColor = new int[] { 28, 74 };
			break;
		case 1079:
			itemDef.modelID = 2582;
			itemDef.name = "Morune Platelegs";
			itemDef.description = "platelegs";
			itemDef.modelZoom = 1740;
			itemDef.modelOffsetY = -8;
			itemDef.rotationY = 444;
			itemDef.maleEquip1 = 268;
			itemDef.femaleEquip1 = 432;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5, 917, 5 };
			itemDef.editedModelColor = new int[] { 61, 41, 57 };
			break;
		case 1127:
			itemDef.modelID = 2605;
			itemDef.name = "Morune Platebody";
			itemDef.description = "platebody";
			itemDef.modelZoom = 1250;
			itemDef.modelOffset1 = -1;
			itemDef.rotationY = 488;
			itemDef.maleEquip1 = 306;
			itemDef.femaleEquip1 = 468;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5, 917, 5 };
			itemDef.editedModelColor = new int[] { 24, 61, 41 };
			break;
		case 1163:
			itemDef.modelID = 2813;
			itemDef.name = "Morune Full Helm";
			itemDef.description = "full helm";
			itemDef.modelZoom = 800;
			itemDef.modelOffsetY = 6;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 152;
			itemDef.rotationY = 160;
			itemDef.maleEquip1 = 218;
			itemDef.femaleEquip1 = 394;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 5 };
			itemDef.editedModelColor = new int[] { 61, 926 };
			break;
		case 4131:
			itemDef.modelID = 5037;
			itemDef.name = "Morune Boots";
			itemDef.description = "boots";
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 4954;
			itemDef.femaleEquip1 = 5031;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 10, 5, 928 };
			itemDef.editedModelColor = new int[] { 5648, 5400, 61 };
			break;
		case 1201:
			itemDef.modelID = 2339;
			itemDef.name = "Morune Kiteshield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1560;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1104;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 486;
			itemDef.femaleEquip1 = 486;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5, 928, 5 };
			itemDef.editedModelColor = new int[] { 7054, 61, 57 };
			break;
		case 1213:
			itemDef.modelID = 2672;
			itemDef.name = "Morune Dagger";
			itemDef.description = " dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 533;
			itemDef.femaleEquip1 = 533;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 927, 926, 925 };
			itemDef.editedModelColor = new int[] { 61, 37, 74, 103 };
			break;
		case 1275:
			itemDef.modelID = 2529;
			itemDef.name = "Morune Pickaxe";
			itemDef.description = " pickaxe";
			itemDef.modelZoom = 1382;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 997;
			itemDef.rotationY = 660;
			itemDef.maleEquip1 = 509;
			itemDef.femaleEquip1 = 509;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 920, 930, 925, 935, 923, 931, 929, 928 };
			itemDef.editedModelColor = new int[] { 61, 51, 76, 74, 60, 121, 99, 42, 41 };
			break;
		case 1359:
			itemDef.modelID = 2544;
			itemDef.name = "Morune Hatchet";
			itemDef.description = " hatchet";
			itemDef.modelZoom = 1060;
			itemDef.modelOffset1 = -15;
			itemDef.rotationX = 1196;
			itemDef.rotationY = 520;
			itemDef.maleEquip1 = 510;
			itemDef.femaleEquip1 = 510;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 451:
			itemDef.modelID = 2748;
			itemDef.name = "Morune ore";
			itemDef.description = "ore";
			itemDef.modelZoom = 1400;
			itemDef.modelOffsetY = 15;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1576;
			itemDef.rotationY = 368;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 928 };
			itemDef.editedModelColor = new int[] { 7062, 45 };
			break;
		case 4834:
			itemDef.modelID = 2748;
			itemDef.name = "Blue dragon ore";
			itemDef.description = "ore";
			itemDef.modelZoom = 1400;
			itemDef.modelOffsetY = 15;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1576;
			itemDef.rotationY = 368;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803, 43803 };
			itemDef.editedModelColor = new int[] { 7062, 45 };
			break;
		case 2363:
			itemDef.modelID = 2408;
			itemDef.name = "Morune bar";
			itemDef.description = "Bar";
			itemDef.modelZoom = 820;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1180;
			itemDef.rotationY = 196;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 7062 };
			break;
		case 4839:
			itemDef.modelID = 2408;
			itemDef.name = "Blue dragon bar";
			itemDef.description = "bar";
			itemDef.modelZoom = 820;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1180;
			itemDef.rotationY = 196;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 43803 };
			itemDef.editedModelColor = new int[] { 7062 };
			break;
		case 9185:
			itemDef.modelID = 16876;
			itemDef.name = "Morune crossbow";
			itemDef.description = " crossbow";
			itemDef.modelZoom = 850;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 269;
			itemDef.rotationY = 323;
			itemDef.maleEquip1 = 16846;
			itemDef.femaleEquip1 = 16846;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5, 5, 5, 928, 928, 928 };
			itemDef.editedModelColor = new int[] { 6447, 6443, 6439, 5656, 5652, 5904 };
			break;
		case 3202:
			itemDef.modelID = 2791;
			itemDef.name = "Morune halberd";
			itemDef.description = "Morune halberd";
			itemDef.modelZoom = 2130;
			itemDef.rotationX = 48;
			itemDef.rotationY = 604;
			itemDef.maleEquip1 = 555;
			itemDef.femaleEquip1 = 555;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 3101:
			itemDef.modelID = 3781;
			itemDef.name = "Morune claws";
			itemDef.description = "claws";
			itemDef.modelZoom = 630;
			itemDef.modelOffsetY = -13;
			itemDef.modelOffset1 = -7;
			itemDef.rotationX = 1340;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 3780;
			itemDef.femaleEquip1 = 42331;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 915, 920, 925, 928 };
			itemDef.editedModelColor = new int[] { 24, 33, 41, 49 };
			break;
		case 1432:
			itemDef.modelID = 2464;
			itemDef.name = "Morune mace";
			itemDef.description = "mace";
			itemDef.modelZoom = 1030;
			itemDef.modelOffsetY = -1;
			itemDef.rotationX = 1188;
			itemDef.rotationY = 392;
			itemDef.maleEquip1 = 502;
			itemDef.femaleEquip1 = 502;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1347:
			itemDef.modelID = 2731;
			itemDef.name = "Morune warhammer";
			itemDef.description = "warhammer";
			itemDef.modelZoom = 1050;
			itemDef.modelOffsetY = 3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 456;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 542;
			itemDef.femaleEquip1 = 542;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1319:
			itemDef.modelID = 2754;
			itemDef.name = "Morune 2h sword";
			itemDef.description = "2h sword";
			itemDef.modelZoom = 1711;
			itemDef.rotationX = 1513;
			itemDef.rotationY = 354;
			itemDef.maleEquip1 = 546;
			itemDef.femaleEquip1 = 546;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 910, 940, 935, 911, 915 };
			itemDef.editedModelColor = new int[] { 75, 41, 127, 82, 48, 57 };
			break;
		case 1185:
			itemDef.modelID = 2720;
			itemDef.name = "Morune sq shield";
			itemDef.description = "sq shield";
			itemDef.modelZoom = 1410;
			itemDef.modelOffsetY = 174;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 60;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 541;
			itemDef.femaleEquip1 = 541;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 57 };
			break;
		case 811:
			itemDef.modelID = 2379;
			itemDef.name = "Morune dart";
			itemDef.description = "dart";
			itemDef.modelZoom = 720;
			itemDef.modelOffsetY = 11;
			itemDef.modelOffset1 = 8;
			itemDef.rotationX = 336;
			itemDef.rotationY = 396;
			itemDef.maleEquip1 = 492;
			itemDef.femaleEquip1 = 492;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 82 };
			itemDef.stackable = true;
			break;
		case 830:
			itemDef.modelID = 2763;
			itemDef.name = "Morune javelin";
			itemDef.description = "javelin";
			itemDef.modelZoom = 1470;
			itemDef.modelOffsetY = 63;
			itemDef.modelOffset1 = -2;
			itemDef.rotationX = 1964;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 547;
			itemDef.femaleEquip1 = 547;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 928 };
			itemDef.editedModelColor = new int[] { 61, 22451 };
			itemDef.stackable = true;
			break;
		case 868:
			itemDef.modelID = 2557;
			itemDef.name = "Morune knife";
			itemDef.description = "knife";
			itemDef.modelZoom = 1080;
			itemDef.modelOffsetX = 1860;
			itemDef.modelOffsetY = -26;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 112;
			itemDef.rotationY = 204;
			itemDef.maleEquip1 = 548;
			itemDef.femaleEquip1 = 548;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928, 928 };
			itemDef.editedModelColor = new int[] { 61, 57 };
			itemDef.stackable = true;
			break;
		case 3072:
			itemDef.modelID = 10435;
			itemDef.name = "Lime Bobble hat";
			itemDef.description = "Bobble hat";
			itemDef.modelZoom = 512;
			itemDef.modelOffsetY = -1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 270;
			itemDef.rotationY = 99;
			itemDef.maleEquip1 = 10532;
			itemDef.femaleEquip1 = 10543;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 22463 };
			itemDef.editedModelColor = new int[] { 7104 };
			break;
		case 3073:
			itemDef.modelID = 10436;
			itemDef.name = "Lime Bobble scarf";
			itemDef.description = "Bobble scarf";
			itemDef.modelZoom = 659;
			itemDef.modelOffsetY = -1;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 207;
			itemDef.rotationY = 511;
			itemDef.maleEquip1 = 10537;
			itemDef.femaleEquip1 = 10547;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 22463 };
			itemDef.editedModelColor = new int[] { 7104 };
			break;
		case 3074:
			itemDef.modelID = 23133;
			itemDef.name = "Blood anchor";
			itemDef.description = "Blood anchor";
			itemDef.modelZoom = 1104;
			itemDef.modelOffsetY = 2;
			itemDef.modelOffset1 = -5;
			itemDef.rotationX = 24;
			itemDef.rotationY = 321;
			itemDef.maleEquip1 = 22787;
			itemDef.femaleEquip1 = 22787;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 918, 920, 916, 922, 914 };
			itemDef.editedModelColor = new int[] { 10283, 10287, 10279, 10291, 10275 };
			break;
		case 3075:
			itemDef.modelID = 10441;
			itemDef.name = "Texas hat";
			itemDef.description = "Texas hat";
			itemDef.modelZoom = 724;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 2006;
			itemDef.rotationY = 93;
			itemDef.maleEquip1 = 10533;
			itemDef.femaleEquip1 = 10544;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 5033, 95 };
			itemDef.editedModelColor = new int[] { 935, 127 };
			break;
		case 3076:
			itemDef.modelID = 10449;
			itemDef.name = "Pink Woolly scarf";
			itemDef.description = "Pink Woolly scarf";
			itemDef.modelZoom = 659;
			itemDef.modelOffsetY = -1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 207;
			itemDef.rotationY = 511;
			itemDef.maleEquip1 = 10539;
			itemDef.femaleEquip1 = 10549;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 54191, 95 };
			itemDef.editedModelColor = new int[] { 11187, 46016 };
			break;
		case 6862:
			itemDef.modelID = 10448;
			itemDef.name = "Pink Woolly hat";
			itemDef.description = "Pink Woolly hat";
			itemDef.modelZoom = 659;
			itemDef.modelOffsetY = -1;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 1602;
			itemDef.rotationY = 226;
			itemDef.maleEquip1 = 10534;
			itemDef.femaleEquip1 = 10545;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Destroy" };
			itemDef.newModelColor = new int[] { 54191, 95 };
			itemDef.editedModelColor = new int[] { 46016, 11187 };
			break;
		case 667:
			itemDef.modelID = 2503;
			itemDef.name = "Blurite sword";
			itemDef.description = "Blurite sword";
			itemDef.modelZoom = 1530;
			itemDef.modelOffsetY = -49;
			itemDef.modelOffset1 = 6;
			itemDef.rotationX = 408;
			itemDef.rotationY = 224;
			itemDef.maleEquip1 = 522;
			itemDef.femaleEquip1 = 522;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5042, 63, 5039, 5038, 12, 5036 };
			itemDef.editedModelColor = new int[] { 43990, 127, 43228, 43107, 39818, 43321 };
			break;
		case 1247:
			itemDef.modelID = 2719;
			itemDef.name = "Morune spear";
			itemDef.description = "spear";
			itemDef.modelZoom = 1490;
			itemDef.modelOffsetX = 144;
			itemDef.modelOffsetY = -4;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 112;
			itemDef.rotationY = 1960;
			itemDef.maleEquip1 = 540;
			itemDef.femaleEquip1 = 540;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 926, 926 };
			itemDef.editedModelColor = new int[] { 33, 28 };
			break;
		case 805:
			itemDef.modelID = 2752;
			itemDef.name = "Morune thrownaxe";
			itemDef.description = "thrownaxe";
			itemDef.modelZoom = 750;
			itemDef.modelOffsetY = 6;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 208;
			itemDef.rotationY = 388;
			itemDef.maleEquip1 = 545;
			itemDef.femaleEquip1 = 545;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 926, 926 };
			itemDef.editedModelColor = new int[] { 61, 57 };
			itemDef.stackable = true;
			break;
		case 1373:
			itemDef.modelID = 2778;
			itemDef.name = "Morune battleaxe";
			itemDef.description = "battleaxe";
			itemDef.modelZoom = 1180;
			itemDef.modelOffsetY = -2;
			itemDef.modelOffset1 = -7;
			itemDef.rotationX = 1128;
			itemDef.rotationY = 420;
			itemDef.maleEquip1 = 550;
			itemDef.femaleEquip1 = 550;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1289:
			itemDef.modelID = 2604;
			itemDef.name = "Morune sword";
			itemDef.description = "sword";
			itemDef.modelZoom = 1513;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1562;
			itemDef.maleEquip1 = 519;
			itemDef.femaleEquip1 = 519;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 926, 920, 915, 900, 910, 905 };
			itemDef.editedModelColor = new int[] { 127, 82, 75, 41, 48, 57 };
			break;
		case 3637:
			itemDef.modelID = 4303;
			itemDef.name = "Happy battleaxe";
			itemDef.description = "Happy battleaxe";
			itemDef.modelZoom = 2256;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 519;
			itemDef.rotationY = 1646;
			itemDef.maleEquip1 = 1837;
			itemDef.femaleEquip1 = 1837;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 18, 82, 11177 };
			itemDef.editedModelColor = new int[] { 0, 78, 920 };
			break;
		case 3638:
			itemDef.modelID = 4450;
			itemDef.name = "Happy mace";
			itemDef.description = "Happy mace";
			itemDef.modelZoom = 1872;
			itemDef.modelOffsetY = 98;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1994;
			itemDef.rotationY = 294;
			itemDef.maleEquip1 = 1898;
			itemDef.femaleEquip1 = 1898;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 18, 82, 11177 };
			itemDef.editedModelColor = new int[] { 0, 78, 920 };
			break;
		case 3639:
			itemDef.modelID = 4616;
			itemDef.name = "Happy spear";
			itemDef.description = "Happy spear";
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 2042;
			itemDef.rotationY = 504;
			itemDef.maleEquip1 = 2224;
			itemDef.femaleEquip1 = 2224;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 18, 82, 11177 };
			itemDef.editedModelColor = new int[] { 0, 78, 920 };
			break;
		case 3640:
			itemDef.modelID = 5312;
			itemDef.name = "Happy sword";
			itemDef.description = "Happy sword";
			itemDef.modelZoom = 2128;
			itemDef.modelOffsetY = 3;
			itemDef.rotationX = 6;
			itemDef.rotationY = 498;
			itemDef.maleEquip1 = 2295;
			itemDef.femaleEquip1 = 2295;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 18, 82, 11177 };
			itemDef.editedModelColor = new int[] { 0, 78, 920 };
			break;
		case 3641:
			itemDef.modelID = 65377;
			itemDef.name = "Blue Dragon chainbody";
			itemDef.description = "Blue Dragon chainbody";
			itemDef.modelZoom = 1100;
			itemDef.modelOffsetY = 2;
			itemDef.rotationY = 568;
			itemDef.maleEquip1 = 65376;
			itemDef.femaleEquip1 = 65376;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3642:
			itemDef.modelID = 65379;
			itemDef.name = "Blue Dragon Full Helm";
			itemDef.description = "Blue Dragon Full Helm";
			itemDef.modelZoom = 789;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 123;
			itemDef.rotationY = 135;
			itemDef.maleEquip1 = 65378;
			itemDef.femaleEquip1 = 65378;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 4083:
			itemDef.modelID = 4937;
			itemDef.name = "Cyan Sled";
			itemDef.description = "Sled";
			itemDef.modelZoom = 1960;
			itemDef.rotationX = 160;
			itemDef.rotationY = 136;
			itemDef.maleEquip1 = 4946;
			itemDef.femaleEquip1 = 4946;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Ride", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 32294, 32182, 32193, 32147 };
			itemDef.editedModelColor = new int[] { 5799, 5559, 6594, 5524 };
			break;
		case 4177:
			itemDef.modelID = 4937;
			itemDef.name = "Lime Sled";
			itemDef.description = "Sled";
			itemDef.modelZoom = 1960;
			itemDef.rotationX = 160;
			itemDef.rotationY = 136;
			itemDef.maleEquip1 = 4946;
			itemDef.femaleEquip1 = 4946;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Ride", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 22054, 21942, 21953, 21907 };
			itemDef.editedModelColor = new int[] { 5799, 5559, 6594, 5524 };
			break;
		case 3643:
			itemDef.modelID = 5413;
			itemDef.name = "Golden Granite maul";
			itemDef.description = "Golden Granite maul";
			itemDef.modelOffsetY = 17;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 36;
			itemDef.rotationY = 308;
			itemDef.maleEquip1 = 5410;
			itemDef.femaleEquip1 = 5410;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 50, 63, 8751, 8743 };
			itemDef.editedModelColor = new int[] { 8481, 8361, 10295, 10287 };
			break;
		case 3644:
			itemDef.modelID = 28133;
			itemDef.name = "Apollo chestplate";
			itemDef.description = "Apollo chestplate";
			itemDef.modelZoom = 984;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 6;
			itemDef.rotationY = 501;
			itemDef.maleEquip1 = 27732;
			itemDef.femaleEquip1 = 27753;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 56000;

			break;
		case 3645:
			itemDef.modelID = 28131;
			itemDef.name = "Apollo tassets";
			itemDef.description = "Apollo tassets";
			itemDef.modelZoom = 854;
			itemDef.modelOffsetY = 3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 2039;
			itemDef.rotationY = 540;
			itemDef.maleEquip1 = 27739;
			itemDef.femaleEquip1 = 27755;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 56000;
			break;
		case 3646:
			itemDef.modelID = 28135;
			itemDef.name = "Apollo boots";
			itemDef.description = "Apollo boots";
			itemDef.modelZoom = 724;
			itemDef.modelOffsetY = -7;
			itemDef.rotationY = 171;
			itemDef.maleEquip1 = 27737;
			itemDef.femaleEquip1 = 19951;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 56000;

			break;
		case 941:
			itemDef.modelID = 65469;
			itemDef.name = "Camo h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 65478;
			itemDef.femaleEquip1 = 65478;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 2749:
			itemDef.modelID = 2438;
			itemDef.name = "Orange h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 5055 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 2750:
			itemDef.modelID = 2438;
			itemDef.name = "Pink h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 54207 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 2751:
			itemDef.modelID = 2438;
			itemDef.name = "Yellow h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 11199 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 2752:
			itemDef.modelID = 2438;
			itemDef.name = "Lime h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 22463 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 2753:
			itemDef.modelID = 2438;
			itemDef.name = "Purple h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 52127 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 2754:
			itemDef.modelID = 2438;
			itemDef.name = "Cyan h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 32703 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 1666:
			itemDef.modelID = 65468;
			itemDef.name = "Rainbow h'ween mask";
			itemDef.description = "Rainbow h'ween mask";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 65477;
			itemDef.femaleEquip1 = 65477;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 1667:
			itemDef.modelID = 65467;
			itemDef.name = "Striped h'ween mask";
			itemDef.description = "Striped h'ween mask";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 65476;
			itemDef.femaleEquip1 = 65476;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3647:
			itemDef.modelID = 65445;
			itemDef.name = "Drygore Full Helmet";
			itemDef.description = "It's a drygore full helmet.";
			itemDef.modelZoom = 725;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65446;
			itemDef.femaleEquip1 = 65446;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3648:
			itemDef.modelID = 65443;
			itemDef.name = "Drygore Platebody";
			itemDef.description = "It's a drygore platebody.";
			itemDef.modelZoom = 1250;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65444;
			itemDef.femaleEquip1 = 65444;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3649:
			itemDef.modelID = 65441;
			itemDef.name = "Drygore Platelegs";
			itemDef.description = "It's a pair of drygore platelegs.";
			itemDef.modelZoom = 1000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65442;
			itemDef.femaleEquip1 = 65442;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3650:
			itemDef.modelID = 65440;
			itemDef.name = "Drygore Boots";
			itemDef.description = "It's a pair of drygore boots.";
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65440;
			itemDef.femaleEquip1 = 65440;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3651:
			itemDef.modelID = 65437;
			itemDef.name = "Drygore Gauntlets";
			itemDef.description = "It's a pair of Drygore Gauntlets.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.maleEquip1 = 65438;
			itemDef.femaleEquip1 = 65438;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3652:
			itemDef.modelID = 65435;
			itemDef.name = "Drygore Wings";
			itemDef.description = "It's a pair of drygore wings.";
			itemDef.modelZoom = 1519;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65436;
			itemDef.femaleEquip1 = 65436;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3653:
			itemDef.modelID = 65433;
			itemDef.name = "Drygore Godsword";
			itemDef.description = "It's a drygore godsword.";
			itemDef.modelZoom = 1711;
			itemDef.rotationX = 1513;
			itemDef.rotationY = 354;
			itemDef.maleEquip1 = 65434;
			itemDef.femaleEquip1 = 65434;
			itemDef.actions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3654:
			itemDef.modelID = 65462;
			itemDef.name = "Purgatory Full Helmet";
			itemDef.description = "It's a purgatory full helmet.";
			itemDef.modelZoom = 1000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65461;
			itemDef.femaleEquip1 = 65461;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3655:
			itemDef.modelID = 65460;
			itemDef.name = "Purgatory Platebody";
			itemDef.description = "It's a purgatory platebody.";
			itemDef.modelZoom = 1400;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65459;
			itemDef.femaleEquip1 = 65459;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3656:
			itemDef.modelID = 65458;
			itemDef.name = "Purgatory Platelegs";
			itemDef.description = "It's a pair of purgatory platelegs.";
			itemDef.modelZoom = 1500;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65457;
			itemDef.femaleEquip1 = 65457;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3657:
			itemDef.modelID = 65455;
			itemDef.name = "Purgatory Boots";
			itemDef.description = "It's a pair of purgatory boots.";
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65455;
			itemDef.femaleEquip1 = 65455;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3658:
			itemDef.modelID = 65454;
			itemDef.name = "Purgatory Gauntlets";
			itemDef.description = "It's a pair of purgatory gauntlets.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.maleEquip1 = 65453;
			itemDef.femaleEquip1 = 65453;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3659:
			itemDef.modelID = 65452;
			itemDef.name = "Purgatory Wings";
			itemDef.description = "It's a pair of purgatory wings.";
			itemDef.modelZoom = 1519;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65451;
			itemDef.femaleEquip1 = 65451;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3660:
			itemDef.modelID = 65450;
			itemDef.name = "Purgatory Longsword";
			itemDef.description = "It's a purgatory longsword.";
			itemDef.modelZoom = 1519;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65449;
			itemDef.femaleEquip1 = 65449;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3661:
			itemDef.modelID = 65448;
			itemDef.name = "Purgatory Offhand";
			itemDef.description = "It's a purgatory offhand.";
			itemDef.modelZoom = 1519;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65447;
			itemDef.femaleEquip1 = 65447;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 8675:
			itemDef.modelID = 65431;
			itemDef.name = "Cleaver Sword";
			itemDef.description = "Cleaver sword";
			itemDef.modelZoom = 550;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1562;
			itemDef.maleEquip1 = 65432;
			itemDef.femaleEquip1 = 65432;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 8677:
			itemDef.modelID = 65430;
			itemDef.name = "Sabre Sword";
			itemDef.description = "Sabre sword";
			itemDef.modelZoom = 550;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1562;
			itemDef.maleEquip1 = 65429;
			itemDef.femaleEquip1 = 65429;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3619:
			itemDef.modelID = 65420;
			itemDef.name = "Camouflage Whip";
			itemDef.description = "It's a Camouflage whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65419;
			itemDef.femaleEquip1 = 65419;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3620:
			itemDef.modelID = 65418;
			itemDef.name = "Camouflage torva platelegs";
			itemDef.description = "It's a set of camouflage torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 65417;
			itemDef.femaleEquip1 = 65417;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3621:
			itemDef.modelID = 65416;
			itemDef.name = "Camouflage torva fullhelm";
			itemDef.description = "It's a camouflage torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 65415;
			itemDef.femaleEquip1 = 65415;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3622:
			itemDef.modelID = 65414;
			itemDef.name = "Camouflage torva platebody";
			itemDef.description = "It's a camouflage torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 65413;
			itemDef.femaleEquip1 = 65413;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 980:
			itemDef.modelID = 65298;
			itemDef.name = "Camouflage bones";
			itemDef.description = "Camouflage bones";
			itemDef.modelZoom = 1830;
			itemDef.rotationX = 648;
			itemDef.rotationY = 216;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 17642:
			itemDef.modelID = 53974;
			itemDef.name = "Winter camo bones";
			itemDef.description = "Camouflage bones";
			itemDef.modelZoom = 1830;
			itemDef.rotationX = 648;
			itemDef.rotationY = 216;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 17644:
			itemDef.modelID = 53979;
			itemDef.name = "Bloodshot camo bones";
			itemDef.description = "Camouflage bones";
			itemDef.modelZoom = 1830;
			itemDef.rotationX = 648;
			itemDef.rotationY = 216;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 3623:
			itemDef.name = "Camouflage gloves";
			itemDef.description = "It's a pair of camouflage gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65412;
			itemDef.maleEquip1 = 65411;
			itemDef.femaleEquip1 = 65411;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3624:
			itemDef.name = "Camouflage boots";
			itemDef.description = "It's a pair of camouflage boots.";
			itemDef.modelID = 65410;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65410;
			itemDef.femaleEquip1 = 65410;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 3625:
			itemDef.modelID = 65409;
			itemDef.name = "Camouflage wings";
			itemDef.description = "It's a pair of camouflage wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65408;
			itemDef.femaleEquip1 = 65408;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3626:
			itemDef.modelID = 65407;
			itemDef.name = "Rainbow Whip";
			itemDef.description = "It's a Rainbow whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65406;
			itemDef.femaleEquip1 = 65406;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3627:
			itemDef.modelID = 65405;
			itemDef.name = "Rainbow torva platelegs";
			itemDef.description = "It's a set of Rainbow torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 65404;
			itemDef.femaleEquip1 = 65404;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3628:
			itemDef.modelID = 65403;
			itemDef.name = "Rainbow torva fullhelm";
			itemDef.description = "It's a Rainbow torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 65402;
			itemDef.femaleEquip1 = 65402;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3629:
			itemDef.modelID = 65401;
			itemDef.name = "Rainbow torva platebody";
			itemDef.description = "It's a Rainbow torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 65400;
			itemDef.femaleEquip1 = 65400;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3630:
			itemDef.name = "Rainbow gloves";
			itemDef.description = "It's a pair of Rainbow gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65399;
			itemDef.maleEquip1 = 65398;
			itemDef.femaleEquip1 = 65398;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3631:
			itemDef.name = "Rainbow boots";
			itemDef.description = "It's a pair of Rainbow boots.";
			itemDef.modelID = 65397;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 65397;
			itemDef.femaleEquip1 = 65397;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 3632:
			itemDef.modelID = 65396;
			itemDef.name = "Rainbow wings";
			itemDef.description = "It's a pair of Rainbow wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65395;
			itemDef.femaleEquip1 = 65395;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3271:
			itemDef.modelID = 65393;
			itemDef.name = "Vortex cowl";
			itemDef.description = "Vortex cowl";
			itemDef.modelZoom = 3250;
			itemDef.rotationY = 532;
			itemDef.rotationX = 14;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65392;
			itemDef.femaleEquip1 = 65392;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.maleDialogue = 62731;
			itemDef.femaleDialogue = 62727;
			itemDef.editedModelColor = new int[2];
			itemDef.newModelColor = new int[2];
			itemDef.editedModelColor[0] = 4550;
			itemDef.newModelColor[0] = 0;
			itemDef.editedModelColor[1] = 4540;
			itemDef.newModelColor[1] = 0;
			break;
		case 3272:
			itemDef.modelID = 65391;
			itemDef.name = "Vortex body";
			itemDef.description = "Vortex body";
			itemDef.modelZoom = 5000;
			itemDef.rotationY = 485;
			itemDef.rotationX = 2042;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 7;
			itemDef.maleEquip1 = 65390;
			itemDef.femaleEquip1 = 65390;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3273:
			itemDef.modelID = 65389;
			itemDef.name = "Vortex chaps";
			itemDef.description = "Vortex chaps";
			itemDef.modelZoom = 7000;
			itemDef.rotationY = 504;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65388;
			itemDef.femaleEquip1 = 65388;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3274:
			itemDef.modelID = 65387;
			itemDef.name = "Vortex boots";
			itemDef.description = "Vortex boots";
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -6;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 160;
			itemDef.rotationY = 152;
			itemDef.maleEquip1 = 65386;
			itemDef.femaleEquip1 = 65386;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 3275:
			itemDef.modelID = 65385;
			itemDef.name = "Vortex vambraces";
			itemDef.description = "Vortex vambraces";
			itemDef.modelZoom = 740;
			itemDef.modelOffsetY = -33;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 1784;
			itemDef.rotationY = 196;
			itemDef.maleEquip1 = 65384;
			itemDef.femaleEquip1 = 65384;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 3276:
			itemDef.modelID = 65383;
			itemDef.name = "Vortex crossbow";
			itemDef.description = "Vortex crossbow";
			itemDef.modelZoom = 1028;
			itemDef.modelOffsetY = -54;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 2021;
			itemDef.rotationY = 249;
			itemDef.maleEquip1 = 65382;
			itemDef.femaleEquip1 = 65382;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 1 };
			itemDef.editedModelColor = new int[] { 0 };
			break;
		case 13664:
			itemDef.modelID = 41993;
			itemDef.name = "Bucks";
			itemDef.description = "1 buck = 1b StreamScape coins.";
			itemDef.modelZoom = 659;
			itemDef.modelOffsetY = -7;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 885;
			itemDef.rotationY = 411;
			itemDef.maleEquip1 = 42110;
			itemDef.femaleEquip1 = 42110;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions[4] = "Drop";
			itemDef.actions[3] = "Add-to-pouch";
			itemDef.colourRedefine = 800;
			itemDef.stackable = true;
			break;
		case 3807:
			itemDef.modelID = 28133;
			itemDef.name = "Gold AP chestplate";
			itemDef.description = "Gold Apollo chestplate";
			itemDef.modelZoom = 984;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 6;
			itemDef.rotationY = 501;
			itemDef.maleEquip1 = 27732;
			itemDef.femaleEquip1 = 27753;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 6000;
			break;
		case 3808:
			itemDef.modelID = 28131;
			itemDef.name = "Gold AP tassets";
			itemDef.description = "Gold Apollo tassets";
			itemDef.modelZoom = 854;
			itemDef.modelOffsetY = 3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 2039;
			itemDef.rotationY = 540;
			itemDef.maleEquip1 = 27739;
			itemDef.femaleEquip1 = 27755;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 6000;

			break;
		case 3809:
			itemDef.modelID = 28135;
			itemDef.name = "Gold AP boots";
			itemDef.description = "Gold Apollo boots";
			itemDef.modelZoom = 724;
			itemDef.modelOffsetY = -7;
			itemDef.rotationY = 171;
			itemDef.maleEquip1 = 27737;
			itemDef.femaleEquip1 = 19951;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.colourRedefine3 = 6000;
			break;
			case 20691:
				itemDef.modelID = 65217;
				itemDef.name = "Gold Ap sword";
				itemDef.description = "It's a sword.";
				itemDef.modelZoom = 1850;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.colourRedefine3 = 6000;

				itemDef.maleEquip1 = 65218;
				itemDef.femaleEquip1 = 65218;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20697:
				itemDef.modelID = 65219;
				itemDef.name = "Elemental sword";
				itemDef.description = "It's a sword.";
				itemDef.modelZoom = 1850;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.colourRedefine3 = 180000;


				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 65220;
				itemDef.femaleEquip1 = 65220;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20693:
				itemDef.modelID = 65221;
				itemDef.name = "Apollo sword";
				itemDef.description = "It's a sword.";
				itemDef.modelZoom = 1850;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.colourRedefine3 = 56000;

				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 65222;
				itemDef.femaleEquip1 = 65222;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20694:
				itemDef.modelID = 65223;
				itemDef.name = "Gold Ap defender";
				itemDef.description = "It's a defender.";
				itemDef.modelZoom = 1750;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.colourRedefine3 = 6000;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 65224;
				itemDef.femaleEquip1 = 65224;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20695:
				itemDef.modelID = 65225;
				itemDef.name = "Elemental defender";
				itemDef.description = "It's a defender.";
				itemDef.modelZoom = 1750;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 65226;
				itemDef.femaleEquip1 = 65226;
				itemDef.colourRedefine3 = 180000;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20696:
				itemDef.modelID = 65227;
				itemDef.name = "Apollo defender";
				itemDef.description = "It's a defender.";
				itemDef.modelZoom = 1750;
				itemDef.rotationY = 595;
				itemDef.rotationX = 750;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.colourRedefine3 = 56000;
				itemDef.maleEquip1 = 65228;
				itemDef.femaleEquip1 = 65228;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;



			case 16000:
				itemDef.modelID = 28133;
				itemDef.name = "Elemental chestplate";
				itemDef.description = "Elemental chestplate";
				itemDef.modelZoom = 984;
				itemDef.modelOffsetY = 4;
				itemDef.modelOffset1 = 1;
				itemDef.rotationX = 6;
				itemDef.rotationY = 501;
				itemDef.maleEquip1 = 27732;
				itemDef.femaleEquip1 = 27753;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.colourRedefine3 = 180000;
				break;
			case 16001:
				itemDef.modelID = 28131;
				itemDef.name = "Elemental tassets";
				itemDef.description = "Elemental tassets";
				itemDef.modelZoom = 854;
				itemDef.modelOffsetY = 3;
				itemDef.modelOffset1 = 3;
				itemDef.rotationX = 2039;
				itemDef.rotationY = 540;
				itemDef.maleEquip1 = 27739;
				itemDef.femaleEquip1 = 27755;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.colourRedefine3 = 180000;
				break;

			case 16002:
				itemDef.modelID = 28135;
				itemDef.name = "Elemental boots";
				itemDef.description = "Gold Apollo boots";
				itemDef.modelZoom = 724;
				itemDef.modelOffsetY = -7;
				itemDef.rotationY = 171;
				itemDef.maleEquip1 = 27737;
				itemDef.femaleEquip1 = 19951;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.colourRedefine3 = 180000;
				break;


		case 3090:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 933;
			itemDef.newModelColor[0] = 7849;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.rotationY = 72;
			itemDef.rotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 189;
			itemDef.femaleEquip1 = 366;
			itemDef.name = "Gold santa hat";
			itemDef.description = "It's a gold santa hat.";
			break;
		case 3811:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			;
			itemDef.editedModelColor[0] = 926;
			itemDef.newModelColor[0] = 7849;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 187;
			itemDef.femaleEquip1 = 363;
			itemDef.name = "Gold partyhat";
			itemDef.description = "It's a gold partyhat.";
			break;
		case 3812:
			itemDef.modelID = 2438;
			itemDef.name = "Gold h'ween mask";
			itemDef.description = "Aaaarrrghhh ... I'm a monster.";
			itemDef.modelZoom = 730;
			itemDef.modelOffsetY = -10;
			itemDef.rotationY = 516;
			itemDef.maleEquip1 = 3188;
			itemDef.femaleEquip1 = 3192;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 7849 };
			itemDef.editedModelColor = new int[] { 1821 };
			break;
		case 3080:
			itemDef.modelID = 65381;
			itemDef.name = "Dark mace";
			itemDef.description = "Dark mace";
			itemDef.modelZoom = 1750;
			itemDef.modelOffsetY = 0;
			itemDef.rotationX = 0;
			itemDef.rotationY = 500;
			itemDef.maleEquip1 = 65380;
			itemDef.femaleEquip1 = 65380;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3081:
			itemDef.name = "AK-47";
			itemDef.description = "It's a AK-47.";
			itemDef.modelZoom = 1284;
			itemDef.rotationY = 1250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.modelID = 15010;
			itemDef.maleEquip1 = 15011;
			itemDef.femaleEquip1 = 15011;
			break;
		case 3082:
			itemDef.name = "AK-47 (G)";
			itemDef.description = "It's a ak-47 w/ a grenade launcher attached.";
			itemDef.modelZoom = 1184;
			itemDef.rotationY = 1250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, "Detach", "Drop" };
			itemDef.modelID = 65292;
			itemDef.maleEquip1 = 15012;
			itemDef.femaleEquip1 = 15012;
			break;
		case 3083:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 32799;
			itemDef.name = "Twisted bow";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 720;
			itemDef.rotationX = 1500;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 1;
			itemDef.femaleEquip1 = 32674;
			itemDef.maleEquip1 = 32674;
			itemDef.description = "A mystical bow carved from the twisted remains of the Great Olm.";
			break;
		case 3086:
			itemDef.modelID = 65375;
			itemDef.name = "American Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 3087:
			itemDef.modelID = 65374;
			itemDef.name = "MewTwo Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 368;
			itemDef.rotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Bury", null, null, null, "Drop" };
			break;
		case 3092:
			itemDef.name = "Desert Eagle";
			itemDef.description = "It's a Destert Eagle.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 360;
			itemDef.rotationX = 720;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.modelID = 15008;
			itemDef.maleEquip1 = 15007;
			itemDef.femaleEquip1 = 15007;
			break;
		case 3135:
			itemDef.name = "Desert Eagle (S)";
			itemDef.description = "It's a Destert Eagle with a suppressor attached.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 360;
			itemDef.rotationX = 720;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, "Detach", "Drop" };
			itemDef.modelID = 65289;
			itemDef.maleEquip1 = 65288;
			itemDef.femaleEquip1 = 65288;
			break;
		case 3242:
			itemDef.modelID = 65372;
			itemDef.name = "Storm bringer";
			itemDef.description = "It's a staff?";
			itemDef.modelZoom = 2000;
			itemDef.rotationX = 750;
			itemDef.rotationY = 360;
			itemDef.maleEquip1 = 65373;
			itemDef.femaleEquip1 = 65373;
			itemDef.actions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3277:
			itemDef.name = "Dragunov";
			itemDef.description = "It's a dragunov.";
			itemDef.modelZoom = 1300;
			itemDef.rotationY = 250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.modelID = 65363;
			itemDef.maleEquip1 = 65364;
			itemDef.femaleEquip1 = 65364;
			break;
		case 3278:
			itemDef.name = "Dragunov (S)";
			itemDef.description = "It's a dragunov w/ a scope attached.";
			itemDef.modelZoom = 800;
			itemDef.rotationY = 250;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, "Detach", "Drop" };
			itemDef.modelID = 65290;
			itemDef.maleEquip1 = 65365;
			itemDef.femaleEquip1 = 65365;
			break;
		case 3279:
			itemDef.name = "Uzi";
			itemDef.description = "It's a uzi.";
			itemDef.modelZoom = 850;
			itemDef.rotationY = 1250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.modelID = 65366;
			itemDef.maleEquip1 = 65367;
			itemDef.femaleEquip1 = 65367;
			break;
		case 3280:
			itemDef.name = "Uzi (S)";
			itemDef.description = "It's a uzi w/ a suppressor attached.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 1250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, "Detach", "Drop" };
			itemDef.modelID = 65291;
			itemDef.maleEquip1 = 65368;
			itemDef.femaleEquip1 = 65368;
			break;
		case 3281:
			itemDef.name = "Scope";
			itemDef.description = "A scope attachment for the dragunov.";
			itemDef.modelZoom = 1500;
			itemDef.rotationY = 250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 65369;
			break;
		case 3282:
			itemDef.name = "Grenade Launcher";
			itemDef.description = "A grenade launcher attachment for the ak-47.";
			itemDef.modelZoom = 1500;
			itemDef.rotationY = 250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 65370;
			break;
		case 3283:
			itemDef.name = "Suppressor";
			itemDef.description = "A suppressor attachment for guns.";
			itemDef.modelZoom = 1000;
			itemDef.rotationY = 250;
			itemDef.rotationX = 360;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 65371;
			break;
		case 3284:
			itemDef.modelID = 65362;
			itemDef.name = "Barbarian 2h axe";
			itemDef.description = "It's a barbarian 2h axe";
			itemDef.modelZoom = 2550;
			itemDef.modelOffsetY = 17;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 720;
			itemDef.rotationY = 360;
			itemDef.maleEquip1 = 65361;
			itemDef.femaleEquip1 = 65361;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3285:
			itemDef.modelID = 65360;
			itemDef.name = "Egyptian sword";
			itemDef.description = "It's a egyptian sword";
			itemDef.modelZoom = 1750;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65359;
			itemDef.femaleEquip1 = 65359;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3286:
			itemDef.modelID = 65358;
			itemDef.name = "Egyptian sword (G)";
			itemDef.description = "It's a egyptian sword (G)";
			itemDef.modelZoom = 1750;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65357;
			itemDef.femaleEquip1 = 65357;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;

		}
		itemDef = hardcodedItems(itemDef, i);
		if (GameClient.ironman > 0)
			if (itemDef.actions != null && itemDef.actions.length > 0)
				for (int a = 0; a < itemDef.actions.length; a++)
					if (itemDef.actions[a] != null && !itemDef.actions[a].isEmpty())
						if (itemDef.actions[a].equalsIgnoreCase("drop"))
							itemDef.actions[a] = "Destroy";

		return itemDef;
	}

	public static ItemDef hardcodedItems(ItemDef itemDef, int id) {
		switch (id) {
		case 3287:
			itemDef.modelID = 65356;
			itemDef.name = "Dragon slayer sword";
			itemDef.description = "Dragon slayer sword";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65355;
			itemDef.femaleEquip1 = 65355;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3288:
			itemDef.modelID = 65354;
			itemDef.name = "Saradomin halberd";
			itemDef.description = "Saradomin halberd";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 25;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65353;
			itemDef.femaleEquip1 = 65353;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3289:
			itemDef.modelID = 3781;
			itemDef.name = "Starter claws";
			itemDef.description = "claws";
			itemDef.modelZoom = 630;
			itemDef.modelOffsetY = -13;
			itemDef.modelOffset1 = -7;
			itemDef.rotationX = 1340;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 3780;
			itemDef.femaleEquip1 = 42331;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 24, 33, 41, 49 };
			break;
		case 3290:
			itemDef.modelID = 65350;
			itemDef.name = "Thunder sword";
			itemDef.description = "Thunder sword";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65349;
			itemDef.femaleEquip1 = 65349;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3291:
			itemDef.modelID = 65348;
			itemDef.name = "Barbarian warhammer";
			itemDef.description = "warhammer";
			itemDef.modelZoom = 1519;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65347;
			itemDef.femaleEquip1 = 65347;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3292:
			itemDef.name = "SAP Glove";
			itemDef.description = "It's a sap glove.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65346;
			itemDef.maleEquip1 = 65345;
			itemDef.femaleEquip1 = 65345;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3293:
			itemDef.name = "Clawed Glove";
			itemDef.description = "It's a clawed glove.";
			itemDef.modelZoom = 750;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1250;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65344;
			itemDef.maleEquip1 = 65343;
			itemDef.femaleEquip1 = 65343;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3294:
			itemDef.modelID = 65342;
			itemDef.name = "Saradomin Kiteshield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1750;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1000;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65341;
			itemDef.femaleEquip1 = 65341;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3295:
			itemDef.modelID = 65340;
			itemDef.name = "Saradomin Kiteshield (G)";
			itemDef.description = "kiteshield (G)";
			itemDef.modelZoom = 1750;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1000;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65339;
			itemDef.femaleEquip1 = 65339;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3296:
			itemDef.modelID = 65338;
			itemDef.name = "Saradomin longsword";
			itemDef.description = "Saradomin longsword";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65337;
			itemDef.femaleEquip1 = 65337;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3297:
			itemDef.modelID = 65336;
			itemDef.name = "Saradomin longsword (G)";
			itemDef.description = "Saradomin longsword (G)";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 595;
			itemDef.rotationX = 1000;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleEquip1 = 65335;
			itemDef.femaleEquip1 = 65335;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3298:
			itemDef.modelID = 65334;
			itemDef.name = "Zamorak Wings";
			itemDef.description = "It's a pair of zamorak wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65333;
			itemDef.femaleEquip1 = 65333;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 3299:
			itemDef.name = "Dragon Rider Cape";
			itemDef.value = 60000;
			itemDef.maleEquip1 = 65331;
			itemDef.femaleEquip1 = 65331;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 65332;
			itemDef.description = "A cape made of ancient, enchanted rocks.";
			itemDef.modelZoom = 2086;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffsetX = 0;
			itemDef.rotationY = 533;
			itemDef.rotationX = 333;
			break;
		case 6756:
			itemDef.modelID = 65330;
			itemDef.name = "Blue Dragon flail";
			itemDef.description = "Flail";
			itemDef.modelZoom = 1440;
			itemDef.modelOffsetY = 32;
			itemDef.rotationX = 352;
			itemDef.rotationY = 272;
			itemDef.maleEquip1 = 65329;
			itemDef.femaleEquip1 = 65329;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11288:
			itemDef.modelID = 52840;
			itemDef.name = "Dark bow (Y)";
			itemDef.description = "Dark bow";
			itemDef.modelZoom = 2128;
			itemDef.rotationX = 1034;
			itemDef.rotationY = 591;
			itemDef.maleEquip1 = 52833;
			itemDef.femaleEquip1 = 52833;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11289:
			itemDef.modelID = 52837;
			itemDef.name = "Dark bow (B)";
			itemDef.description = "Dark bow";
			itemDef.modelZoom = 2128;
			itemDef.rotationX = 1034;
			itemDef.rotationY = 591;
			itemDef.maleEquip1 = 52832;
			itemDef.femaleEquip1 = 52832;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11290:
			itemDef.modelID = 52839;
			itemDef.name = "Dark bow (G)";
			itemDef.description = "Dark bow";
			itemDef.modelZoom = 2128;
			itemDef.rotationX = 1034;
			itemDef.rotationY = 591;
			itemDef.maleEquip1 = 52831;
			itemDef.femaleEquip1 = 52831;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11291:
			itemDef.modelID = 52838;
			itemDef.name = "Dark bow (W)";
			itemDef.description = "Dark bow";
			itemDef.modelZoom = 2128;
			itemDef.rotationX = 1034;
			itemDef.rotationY = 591;
			itemDef.maleEquip1 = 52834;
			itemDef.femaleEquip1 = 52834;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11292:
			itemDef.modelID = 12277;
			itemDef.name = "Gold boxing gloves";
			itemDef.description = "Gold boxing gloves";
			itemDef.modelZoom = 789;
			itemDef.modelOffsetX = 69;
			itemDef.modelOffsetY = 15;
			itemDef.modelOffset1 = 4;
			itemDef.rotationX = 1674;
			itemDef.rotationY = 346;
			itemDef.maleEquip1 = 13317;
			itemDef.femaleEquip1 = 13329;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 7849 };
			itemDef.editedModelColor = new int[] { 947 };
			break;
		case 3869:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 65323;
			itemDef.modelZoom = 440;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65328;
			itemDef.femaleEquip1 = 65328;
			itemDef.name = "Lava partyhat";
			itemDef.description = "It's a lava partyhat.";
			break;/**
					 * case 3870: itemDef.groundActions = new String[5]; itemDef.groundActions[2] =
					 * "Take"; itemDef.actions = new String[5]; itemDef.actions[1] = "Wear";
					 * itemDef.actions[4] = "Drop"; itemDef.modelID = 65322; itemDef.modelZoom =
					 * 440; itemDef.rotationY = 121; itemDef.rotationX = 1845; itemDef.modelOffset1
					 * = 10; itemDef.modelOffsetY = 5; itemDef.maleEquip1 = 65321;
					 * itemDef.femaleEquip1 = 65321; itemDef.name = "Stone partyhat";
					 * itemDef.description = "It's a stone partyhat."; break; case 3871:
					 * itemDef.groundActions = new String[5]; itemDef.groundActions[2] = "Take";
					 * itemDef.actions = new String[5]; itemDef.actions[1] = "Wear";
					 * itemDef.actions[4] = "Drop"; itemDef.modelID = 65320; itemDef.modelZoom =
					 * 440; itemDef.rotationY = 121; itemDef.rotationX = 1845; itemDef.modelOffset1
					 * = 10; itemDef.modelOffsetY = 5; itemDef.maleEquip1 = 65319;
					 * itemDef.femaleEquip1 = 65319; itemDef.name = "Water partyhat";
					 * itemDef.description = "It's a water partyhat."; break;
					 */
		case 3308:
			itemDef.modelID = 65318;
			itemDef.name = "Bandos crossbow";
			itemDef.description = "crossbow";
			itemDef.modelZoom = 1250;
			itemDef.modelOffset1 = 20;
			itemDef.rotationX = 269;
			itemDef.rotationY = 323;
			itemDef.maleEquip1 = 65317;
			itemDef.femaleEquip1 = 65317;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6806, 7054, 5002, 928, 928, 928 };
			itemDef.editedModelColor = new int[] { 6447, 6443, 6439, 5656, 5652, 5904 };
			break;
		case 3244:
			itemDef.modelID = 65316;
			itemDef.name = "Universal shield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1560;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1104;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65315;
			itemDef.femaleEquip1 = 65315;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3078:
			itemDef.modelID = 65314;
			itemDef.name = "Akimbo Axes";
			itemDef.description = "Akimbo Axes";
			itemDef.modelZoom = 1820;
			itemDef.modelOffsetY = -9;
			itemDef.modelOffset1 = 5;
			itemDef.rotationX = 1212;
			itemDef.rotationY = 272;
			itemDef.maleEquip1 = 65313;
			itemDef.femaleEquip1 = 65313;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3079:
			itemDef.name = "Purple winged boots";
			itemDef.description = "It's a pair of boots.";
			itemDef.modelID = 29249;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 29249;
			itemDef.femaleEquip1 = 29249;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 3084:
			itemDef.name = "Red winged boots";
			itemDef.description = "It's a pair of boots.";
			itemDef.modelID = 29250;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 29250;
			itemDef.femaleEquip1 = 29250;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 20080:
			itemDef.modelID = 65295;
			itemDef.name = "Blood shortbow";
			itemDef.description = "Blood shortbow";
			itemDef.modelZoom = 1200;
			itemDef.modelOffsetY = 2;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 124;
			itemDef.rotationY = 508;
			itemDef.maleEquip1 = 65296;
			itemDef.femaleEquip1 = 65296;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 2870:
			itemDef.name = "Camo winged boots";
			itemDef.description = "It's a pair of boots.";
			itemDef.modelID = 29252;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 29252;
			itemDef.femaleEquip1 = 29252;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 3301:
			itemDef.modelID = 65310;
			itemDef.name = "Bandos shield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1760;
			itemDef.modelOffsetY = 70;
			itemDef.modelOffset1 = 0;
			itemDef.rotationX = 750;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65309;
			itemDef.femaleEquip1 = 65309;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 3302:
			itemDef.modelID = 65308;
			itemDef.name = "Dragon slayer shield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1560;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1104;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65307;
			itemDef.femaleEquip1 = 65307;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 84:
			itemDef.modelID = 47349;
			itemDef.name = "Magma shield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1450;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1825;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 17120;
			itemDef.femaleEquip1 = 17120;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 4204:
			itemDef.modelID = 2838;
			itemDef.name = "Magma battleaxe";
			itemDef.description = "Magma battleaxe";
			itemDef.modelZoom = 1660;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 2020;
			itemDef.rotationY = 420;
			itemDef.maleEquip1 = 3902;
			itemDef.femaleEquip1 = 3902;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3300:
			itemDef.modelID = 65306;
			itemDef.name = "Bandos Scimitar";
			itemDef.description = "Bandos scimitar";
			itemDef.modelZoom = 2000;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 250;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65305;
			itemDef.femaleEquip1 = 65305;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3303:
			itemDef.modelID = 65304;
			itemDef.name = "Ancient scimitar";
			itemDef.description = "Ancient sword";
			itemDef.modelZoom = 2300;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 0;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65303;
			itemDef.femaleEquip1 = 65303;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3304:
			itemDef.modelID = 65302;
			itemDef.name = "Baseball bat";
			itemDef.description = "Bastball bat";
			itemDef.modelZoom = 2000;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1562;
			itemDef.maleEquip1 = 65301;
			itemDef.femaleEquip1 = 65301;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 3305:
			itemDef.modelID = 65300;
			itemDef.name = "Bandos war spear";
			itemDef.description = "Bandos war spear";
			itemDef.modelZoom = 2750;
			itemDef.modelOffsetX = 144;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 112;
			itemDef.rotationY = 1960;
			itemDef.maleEquip1 = 65299;
			itemDef.femaleEquip1 = 65299;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 2633:
			itemDef.modelID = 65294;
			itemDef.name = "Bulls Hat";
			itemDef.description = "Bulls Hat";
			itemDef.modelZoom = 560;
			itemDef.modelOffsetY = -4;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 1936;
			itemDef.rotationY = 136;
			itemDef.maleEquip1 = 65293;
			itemDef.femaleEquip1 = 65293;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 421:
			itemDef.modelID = 2508;
			itemDef.name = "Gold Chain";
			itemDef.description = "Gold Chain";
			itemDef.modelZoom = 620;
			itemDef.modelOffsetY = 16;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 68;
			itemDef.rotationY = 424;
			itemDef.maleEquip1 = 282;
			itemDef.femaleEquip1 = 282;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 85:
			itemDef.modelID = 2595;
			itemDef.name = "Donor Rank";
			itemDef.description = "Donor Rank";
			itemDef.modelZoom = 775;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 20;
			itemDef.rotationY = 328;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Claim", null, null, null, "Drop" };
			break;
		case 2572:
			itemDef.actions = new String[] { null, "Wear", "Operate", null, "Drop", null };
			break;
		case 596:
			itemDef.modelID = 2345;
			itemDef.name = "Ring of Riches";
			itemDef.description = "Ring of Riches";
			itemDef.actions = new String[] { null, "Wear", "Operate", "Morph", "Drop", null };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.modelZoom = 775;
			itemDef.modelOffset1 = -2;
			itemDef.rotationX = 20;
			itemDef.rotationY = 328;
			break;
		case 275:
			itemDef.modelID = 2679;
			itemDef.name = "Deluxe Donor Rank";
			itemDef.description = "Deluxe Donor Rank";
			itemDef.modelZoom = 2550;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 20;
			itemDef.rotationY = 328;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Claim", null, null, null, "Drop" };
			break;
		case 293:
			itemDef.modelID = 62377;
			itemDef.name = "Sponsor Rank";
			itemDef.description = "Sponsor Rank";
			itemDef.modelZoom = 700;
			itemDef.modelOffsetY = 5;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 20;
			itemDef.rotationY = 328;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Claim", null, null, null, "Drop" };
			break;
		case 298:
			itemDef.modelID = 65256;
			itemDef.name = "Death Kiteshield";
			itemDef.description = "Death Kiteshield";
			itemDef.modelZoom = 1360;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 0;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65255;
			itemDef.femaleEquip1 = 65255;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 423:
			itemDef.modelID = 65257;
			itemDef.name = "Thunder Gun";
			itemDef.description = "Thunder Gun";
			itemDef.modelZoom = 2000;
			itemDef.rotationY = 500;
			itemDef.rotationX = 150;
			itemDef.maleEquip1 = 65258;
			itemDef.femaleEquip1 = 65258;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
			case 422:
				itemDef.modelID = 65257;
				itemDef.name = "Lava Gun";
				itemDef.description = "Lava Gun";
				itemDef.modelZoom = 2000;
				itemDef.rotationY = 500;
				itemDef.rotationX = 150;
				itemDef.maleEquip1 = 65258;
				itemDef.colourRedefine2 = 800;
				itemDef.femaleEquip1 = 65258;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
            case 21005:
                itemDef.modelID = 6780;
                itemDef.name = "@red@Fire Draconian compbow";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.rotationY = 296;
                itemDef.rotationX = 377;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
                itemDef.colourRedefine3 = 899;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 6813;
                itemDef.femaleEquip1 = 6813;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 21000:
                itemDef.modelID = 54107;
                itemDef.name = "@red@Fire Draconian coif";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 789;
                itemDef.rotationY = 135;
                itemDef.rotationX = 148;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 1;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 55744;
                itemDef.femaleEquip1 = 56447;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 21001:
                itemDef.modelID = 44627;
                itemDef.name = "@red@Fire Draconian body";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1513;
                itemDef.rotationX = 2041;
                itemDef.rotationY = 593;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -11;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.femaleEquip1 = 44812;
                itemDef.maleEquip1 = 44812;
                itemDef.colourRedefine3 = 899;

                break;

            case 21002:
                itemDef.name = "@red@Fire Draconian chaps";
                itemDef.description = "A custom item.";
                itemDef.modelID = 44000;
                itemDef.modelZoom = 1711;
                itemDef.rotationX = 0;
                itemDef.rotationY = 360;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -11;
                itemDef.stackable = false;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.femaleEquip1 = 44768;
                itemDef.maleEquip1 = 44768;
                itemDef.colourRedefine3 = 899;

                break;

            case 21003:
                itemDef.modelID = 54111;
                itemDef.name = "@red@Fire Draconian ranger boots";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 770;
                itemDef.rotationY = 188;
                itemDef.rotationX = 1988;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -4;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 55703;
                itemDef.femaleEquip1 = 56357;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 21004:
                itemDef.modelID = 54052;
                itemDef.name = "@red@Fire Draconian vambraces";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 724;
                itemDef.rotationY = 536;
                itemDef.rotationX = 1939;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 3;
                itemDef.colourRedefine3 = 899;
                ;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 4953;
                itemDef.femaleEquip1 = 4953;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;


            case 21006:
                itemDef.modelID = 54093;
                itemDef.name = "@red@Fire Draconian hood";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1025;
                itemDef.rotationY = 215;
                itemDef.rotationX = 162;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -43;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 55746;
                itemDef.femaleEquip1 = 56453;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 21007:
                itemDef.modelID = 54097;
                itemDef.name = "@red@Fire Draconian robe top";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.rotationY = 485;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 13;
                itemDef.colourRedefine3 = 899;

                itemDef.maleEquip1 = 42627;
                itemDef.femaleEquip1 = 42627;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.stackable = false;
                break;
            case 21008:
                itemDef.modelID = 54009;
                itemDef.name = "@red@Fire Draconian robe bottom";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1974;
                itemDef.rotationY = 377;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 16;
                itemDef.colourRedefine3 = 899;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 42634;
                itemDef.femaleEquip1 = 42634;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 21009:
                itemDef.modelID = 54177;
                itemDef.name = "@red@Fire Draconian shoes";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 770;
                itemDef.rotationY = 189;
                itemDef.rotationX = 1988;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -22;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 55681;
                itemDef.femaleEquip1 = 56367;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 21010:
                itemDef.modelID = 54121;
                itemDef.name = "@red@Fire Draconian mage gloves";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 830;
                itemDef.rotationY = 536;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 3;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 4953;
                itemDef.femaleEquip1 = 4953;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 21011:
                itemDef.modelID = 54391;
                itemDef.name = "@red@Fire Draconian staff";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.rotationY = 391;
                itemDef.rotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.colourRedefine3 = 899;

                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 55909;
                itemDef.femaleEquip1 = 55909;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;



			case 21040:
				itemDef.modelID = 54107;
				itemDef.name = "@gre@Toxic Draconian coif";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 135;
				itemDef.rotationX = 148;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55744;
				itemDef.femaleEquip1 = 56447;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21041:
				itemDef.modelID = 44627;
				itemDef.name = "@gre@Toxic Draconian body";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1513;
				itemDef.rotationX = 2041;
				itemDef.rotationY = 593;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.stackable = false;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.femaleEquip1 = 44812;
				itemDef.maleEquip1 = 44812;
				itemDef.colourRedefine3 = 210770;

				break;

			case 21042:
				itemDef.name = "@gre@Toxic Draconian chaps";
				itemDef.description = "A custom item.";
				itemDef.modelID = 44000;
				itemDef.modelZoom = 1711;
				itemDef.rotationX = 0;
				itemDef.rotationY = 360;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.stackable = false;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.femaleEquip1 = 44768;
				itemDef.maleEquip1 = 44768;
				itemDef.colourRedefine3 = 210770;

				break;

			case 21043:
				itemDef.modelID = 54111;
				itemDef.name = "@gre@Toxic Draconian ranger boots";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 770;
				itemDef.rotationY = 188;
				itemDef.rotationX = 1988;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -4;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55703;
				itemDef.femaleEquip1 = 56357;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 21044:
				itemDef.modelID = 54052;
				itemDef.name = "@gre@Toxic Draconian vambraces";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 724;
				itemDef.rotationY = 536;
				itemDef.rotationX = 1939;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 3;
				itemDef.colourRedefine3 = 210770;
				;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4953;
				itemDef.femaleEquip1 = 4953;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 21045:
				itemDef.modelID = 6780;
				itemDef.name = "@gre@Toxic Draconian compbow";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 296;
				itemDef.rotationX = 377;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -33;
				itemDef.colourRedefine3 = 210770;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 6813;
				itemDef.femaleEquip1 = 6813;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
				
			case 21046:
				itemDef.modelID = 54093;
				itemDef.name = "@gre@Toxic Draconian hood";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1025;
				itemDef.rotationY = 215;
				itemDef.rotationX = 162;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -43;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55746;
				itemDef.femaleEquip1 = 56453;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21047:
				itemDef.modelID = 54097;
				itemDef.name = "@gre@Toxic Draconian robe top";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 13;
				itemDef.colourRedefine3 = 210770;

				itemDef.maleEquip1 = 42627;
				itemDef.femaleEquip1 = 42627;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.stackable = false;
				break;
			case 21048:
				itemDef.modelID = 54009;
				itemDef.name = "@gre@Toxic Draconian robe bottom";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1974;
				itemDef.rotationY = 377;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 16;
				itemDef.colourRedefine3 = 210770;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 42634;
				itemDef.femaleEquip1 = 42634;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21049:
				itemDef.modelID = 54177;
				itemDef.name = "@gre@Toxic Draconian shoes";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 770;
				itemDef.rotationY = 189;
				itemDef.rotationX = 1988;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = -22;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55681;
				itemDef.femaleEquip1 = 56367;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21050:
				itemDef.modelID = 54121;
				itemDef.name = "@gre@Toxic Draconian mage gloves";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 830;
				itemDef.rotationY = 536;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 3;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4953;
				itemDef.femaleEquip1 = 4953;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21051:
				itemDef.modelID = 54391;
				itemDef.name = "@gre@Toxic Draconian staff";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1650;
				itemDef.rotationY = 391;
				itemDef.rotationX = 391;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.colourRedefine3 = 210770;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55909;
				itemDef.femaleEquip1 = 55909;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;


            case 21052:
                itemDef.modelID = 21025;
                itemDef.name = "@blu@Mystic staff";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.rotationY = 391;
                itemDef.rotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 21026;
                itemDef.femaleEquip1 = 21026;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

			case 6904:
				itemDef.name = "@or1@Golden Tormented Demon bones";
				itemDef.colourRedefine3 = 6500;
				break;
            case 21053:
                itemDef.modelID = 21060;
                itemDef.name = "@or1@Golden Karambid";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.rotationY = 391;
                itemDef.rotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.membersObject = true;
                itemDef.colourRedefine3 = 6500;
                itemDef.maleEquip1 = 21061;
                itemDef.femaleEquip1 = 21061;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 13047:
                itemDef.colourRedefine3 = 6500;
                itemDef.name = "Golden Abyssal dagger";
                itemDef.modelZoom = 1347;
                itemDef.rotationY = 1589;
                itemDef.rotationX = 781;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -5;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
                itemDef.modelID = 29598;
                itemDef.maleEquip1 = 29425;
                itemDef.femaleEquip1 = 29425;
                break;

            case 21054:
                itemDef.modelID = 21025;
                itemDef.name = "@or1@Golden Mystic staff";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.rotationY = 391;
                itemDef.rotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.membersObject = true;
                itemDef.colourRedefine3 = 6000;

                itemDef.maleEquip1 = 21026;
                itemDef.femaleEquip1 = 21026;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 21055:
                itemDef.modelID = 21025;
                itemDef.name = "@blu@Mystic staff";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.rotationY = 391;
                itemDef.rotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.colourRedefine3 = 180000;

                itemDef.membersObject = true;
                itemDef.maleEquip1 = 21026;
                itemDef.femaleEquip1 = 21026;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 21020:
				itemDef.modelID = 54107;
				itemDef.name = "@cya@Ice Draconian coif";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 135;
				itemDef.rotationX = 148;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55744;
				itemDef.femaleEquip1 = 56447;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21021:
				itemDef.modelID = 44627;
				itemDef.name = "@cya@Ice Draconian body";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1513;
				itemDef.rotationX = 2041;
				itemDef.rotationY = 593;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.stackable = false;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.femaleEquip1 = 44812;
				itemDef.maleEquip1 = 44812;
				itemDef.colourRedefine3 = 97000;

				break;

			case 21022:
				itemDef.name = "@cya@Ice Draconian chaps";
				itemDef.description = "A custom item.";
				itemDef.modelID = 44000;
				itemDef.modelZoom = 1711;
				itemDef.rotationX = 0;
				itemDef.rotationY = 360;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.stackable = false;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.femaleEquip1 = 44768;
				itemDef.maleEquip1 = 44768;
				itemDef.colourRedefine3 = 97000;

				break;

			case 21023:
				itemDef.modelID = 54111;
				itemDef.name = "@cya@Ice Draconian ranger boots";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 770;
				itemDef.rotationY = 188;
				itemDef.rotationX = 1988;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -4;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55703;
				itemDef.femaleEquip1 = 56357;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 21024:
				itemDef.modelID = 54052;
				itemDef.name = "@cya@Ice Draconian vambraces";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 724;
				itemDef.rotationY = 536;
				itemDef.rotationX = 1939;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 3;
				itemDef.colourRedefine3 = 97000;
				;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4953;
				itemDef.femaleEquip1 = 4953;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 21025:
				itemDef.modelID = 6780;
				itemDef.name = "@cya@Ice Draconian compbow";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 296;
				itemDef.rotationX = 377;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -33;
				itemDef.colourRedefine3 = 97000;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 6813;
				itemDef.femaleEquip1 = 6813;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;


			case 21026:
				itemDef.modelID = 54093;
				itemDef.name = "@cya@Ice Draconian hood";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1025;
				itemDef.rotationY = 215;
				itemDef.rotationX = 162;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -43;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55746;
				itemDef.femaleEquip1 = 56453;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21027:
				itemDef.modelID = 54097;
				itemDef.name = "@cya@Ice Draconian robe top";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 13;
				itemDef.colourRedefine3 = 97000;

				itemDef.maleEquip1 = 42627;
				itemDef.femaleEquip1 = 42627;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.stackable = false;
				break;
			case 21028:
				itemDef.modelID = 54009;
				itemDef.name = "@cya@Ice Draconian robe bottom";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1974;
				itemDef.rotationY = 377;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 16;
				itemDef.colourRedefine3 = 97000;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 42634;
				itemDef.femaleEquip1 = 42634;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21029:
				itemDef.modelID = 54177;
				itemDef.name = "@cya@Ice Draconian shoes";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 770;
				itemDef.rotationY = 189;
				itemDef.rotationX = 1988;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = -22;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55681;
				itemDef.femaleEquip1 = 56367;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21030:
				itemDef.modelID = 54121;
				itemDef.name = "@cya@Ice Draconian mage gloves";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 830;
				itemDef.rotationY = 536;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 3;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4953;
				itemDef.femaleEquip1 = 4953;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 21031:
				itemDef.modelID = 54391;
				itemDef.name = "@cya@Ice Draconian staff";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1650;
				itemDef.rotationY = 391;
				itemDef.rotationX = 391;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.colourRedefine3 = 97000;

				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55909;
				itemDef.femaleEquip1 = 55909;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;



			case 432:
			itemDef.modelID = 65259;
			itemDef.name = "Skateboard";
			itemDef.description = "Skateboard";
			itemDef.modelZoom = 2500;
			itemDef.rotationX = 125;
			itemDef.rotationY = 424;
			itemDef.maleEquip1 = 65259;
			itemDef.femaleEquip1 = 65259;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 601:
			itemDef.modelID = 65260;
			itemDef.name = "Ray Gun";
			itemDef.description = "Ray Gun";
			itemDef.modelZoom = 1150;
			itemDef.rotationY = 360;
			itemDef.rotationX = 720;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffsetY = -18;
			itemDef.maleEquip1 = 65261;
			itemDef.femaleEquip1 = 65261;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 605:
			itemDef.modelID = 65263;
			itemDef.name = "Venom Kiteshield";
			itemDef.description = "Venom Kiteshield";
			itemDef.modelZoom = 1660;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 0;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65262;
			itemDef.femaleEquip1 = 65262;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 709:
			itemDef.modelID = 65265;
			itemDef.name = "Bock Kiteshield";
			itemDef.description = "Bock Kiteshield";
			itemDef.modelZoom = 1660;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 0;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65264;
			itemDef.femaleEquip1 = 65264;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 758:
			itemDef.modelID = 65266;
			itemDef.name = "Star Katana";
			itemDef.description = "Star Katana";
			itemDef.modelZoom = 1845;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65267;
			itemDef.femaleEquip1 = 65267;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 759:
			itemDef.modelID = 65269;
			itemDef.name = "Iphone 7";
			itemDef.description = "Iphone 7";
			itemDef.modelZoom = 1888;
			itemDef.rotationX = 0;
			itemDef.rotationY = 424;
			itemDef.maleEquip1 = 65268;
			itemDef.femaleEquip1 = 65268;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 788:
			itemDef.modelID = 65273;
			itemDef.name = "Newt Blade";
			itemDef.description = "Newt Blade";
			itemDef.modelZoom = 1645;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65272;
			itemDef.femaleEquip1 = 65272;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 983:
			itemDef.modelID = 65275;
			itemDef.name = "Tiger Katana";
			itemDef.description = "Tiger Katana";
			itemDef.modelZoom = 1845;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65274;
			itemDef.femaleEquip1 = 65274;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 20079:
			itemDef.modelID = 65276;
			itemDef.name = "Grimy Blood Plant";
			itemDef.description = "Grimy Blood Plant";
			itemDef.modelZoom = 920;
			itemDef.rotationX = 68;
			itemDef.rotationY = 424;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;
		case 20078:
			itemDef.modelID = 65277;
			itemDef.name = "Blood Plant";
			itemDef.description = "Blood Plant";
			itemDef.modelZoom = 920;
			itemDef.rotationX = 68;
			itemDef.rotationY = 424;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;
		case 5:
			itemDef.modelID = 65279;
			itemDef.name = "Skairu Staff";
			itemDef.description = "Skairu Staff";
			itemDef.modelZoom = 1590;
			itemDef.modelOffsetX = 144;
			itemDef.modelOffsetY = -4;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 112;
			itemDef.rotationY = 1960;
			itemDef.maleEquip1 = 65278;
			itemDef.femaleEquip1 = 65278;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 20075:
			itemDef.modelID = 65280;
			itemDef.name = "Blood Logs";
			itemDef.description = "Blood Logs";
			itemDef.modelZoom = 700;
			itemDef.modelOffsetY = -7;
			itemDef.rotationX = 1852;
			itemDef.rotationY = 120;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;
		case 20073:
			itemDef.modelID = 65281;
			itemDef.name = "Blood Bow (U)";
			itemDef.description = "Blood bow (U).";
			itemDef.modelZoom = 1820;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = 8;
			itemDef.rotationX = 983;
			itemDef.rotationY = 471;
			itemDef.stackable = false;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;
		case 20074:
			itemDef.modelID = 65282;
			itemDef.name = "Blood Flax";
			itemDef.description = "Used to make blood bow strings.";
			itemDef.modelZoom = 789;
			itemDef.modelOffsetX = 97;
			itemDef.modelOffsetY = -1;
			itemDef.modelOffset1 = 8;
			itemDef.rotationX = 1770;
			itemDef.rotationY = 581;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;
		case 20082:
			itemDef.modelID = 65283;
			itemDef.name = "Blood bow string";
			itemDef.description = "Blood bow string";
			itemDef.modelZoom = 630;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1881;
			itemDef.rotationY = 162;
			itemDef.maleEquip1 = 65283;
			itemDef.femaleEquip1 = 65283;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 1543:
			itemDef.modelID = 65285;
			itemDef.name = "Beatz By Dr.Dre";
			itemDef.description = "Beatz By Dr.Dre";
			itemDef.modelZoom = 620;
			itemDef.modelOffsetY = 16;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 68;
			itemDef.rotationY = 424;
			itemDef.maleEquip1 = 65284;
			itemDef.femaleEquip1 = 65284;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 1544:
			itemDef.modelID = 65287;
			itemDef.name = "Wrathful Blade";
			itemDef.description = "Wrathful Blade";
			itemDef.modelZoom = 1645;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65286;
			itemDef.femaleEquip1 = 65286;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 625:
			itemDef.modelID = 2536;
			itemDef.name = "Winter Camo Whip";
			itemDef.description = "It's a whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 24007;
			itemDef.femaleEquip1 = 24007;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 624:
			itemDef.modelID = 2560;
			itemDef.name = "Winter Camo Torva Platelegs";
			itemDef.description = "It's a set of torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 2547;
			itemDef.femaleEquip1 = 2547;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 623:
			itemDef.modelID = 14342;
			itemDef.name = "Winter Camo Torva Fullhelm";
			itemDef.description = "It's a torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 2684;
			itemDef.femaleEquip1 = 2684;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 621:
			itemDef.modelID = 2336;
			itemDef.name = "Winter Camo Torva Platebody";
			itemDef.description = "It's a torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 2575;
			itemDef.femaleEquip1 = 2575;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 620:
			itemDef.name = "Winter Camo Gloves";
			itemDef.description = "It's a pair of gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 2623;
			itemDef.maleEquip1 = 2714;
			itemDef.femaleEquip1 = 2714;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 619:
			itemDef.name = "Winter Camo Boots";
			itemDef.description = "It's a pair of boots.";
			itemDef.modelID = 14343;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 14343;
			itemDef.femaleEquip1 = 14343;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 618:
			itemDef.modelID = 2600;
			itemDef.name = "Winter Camo Wings";
			itemDef.description = "It's a pair of wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 3155;
			itemDef.femaleEquip1 = 3155;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 665:
			itemDef.modelID = 2774;
			itemDef.name = "Bloodshot Camo Whip";
			itemDef.description = "It's a whip.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 1314;
			itemDef.femaleEquip1 = 1314;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 666:
			itemDef.modelID = 17291;
			itemDef.name = "Bloodshot Camo Torva Platelegs";
			itemDef.description = "It's a set of torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.rotationY = 474;
			itemDef.rotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleEquip1 = 202;
			itemDef.femaleEquip1 = 202;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 669:
			itemDef.modelID = 17298;
			itemDef.name = "Bloodshot Camo Torva Fullhelm";
			itemDef.description = "It's a torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.rotationY = 0;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -14;
			itemDef.maleEquip1 = 2385;
			itemDef.femaleEquip1 = 2385;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 670:
			itemDef.modelID = 17290;
			itemDef.name = "Bloodshot Camo Torva Platebody";
			itemDef.description = "It's a torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -8;
			itemDef.modelZoom = 1513;
			itemDef.rotationY = 566;
			itemDef.rotationX = 0;
			itemDef.maleEquip1 = 17297;
			itemDef.femaleEquip1 = 17297;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 671:
			itemDef.name = "Bloodshot Camo Gloves";
			itemDef.description = "It's a pair of gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 2786;
			itemDef.maleEquip1 = 2521;
			itemDef.femaleEquip1 = 2521;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 672:
			itemDef.name = "Bloodshot Camo Boots";
			itemDef.description = "It's a pair of boots.";
			itemDef.modelID = 2483;
			itemDef.modelZoom = 855;
			itemDef.rotationY = 215;
			itemDef.rotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = -32;
			itemDef.maleEquip1 = 2483;
			itemDef.femaleEquip1 = 2483;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 673:
			itemDef.modelID = 377;
			itemDef.name = "Bloodshot Camo Wings";
			itemDef.description = "It's a pair of wings.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 265;
			itemDef.femaleEquip1 = 265;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 21636:
			itemDef.modelID = 62381;
			itemDef.name = "Knuck Knife";
			itemDef.description = "Knuck Knife";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 62378;
			itemDef.femaleEquip1 = 62378;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 695:
			itemDef.modelID = 2522;
			itemDef.name = "Staff of gods";
			itemDef.description = "godly staff";
			itemDef.modelZoom = 1874;
			itemDef.modelOffsetY = 2;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 1499;
			itemDef.rotationY = 292;
			itemDef.maleEquip1 = 2493;
			itemDef.femaleEquip1 = 2493;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 9707:
			itemDef.modelID = 54271;
			itemDef.name = "@red@Co@cya@lor@gre@ful @yel@wh@or2@ip";
			itemDef.description = "Colorful whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 54350;
			itemDef.femaleEquip1 = 54350;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11529:
			itemDef.modelID = 26665;
			itemDef.name = "@red@Ras@yel@ta w@gre@hip";
			itemDef.description = "Rasta whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 2816;
			itemDef.femaleEquip1 = 2816;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11526:
			itemDef.modelID = 2573;
			itemDef.name = "@or2@Lava @bla@whip";
			itemDef.description = "Lava whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 2778;
			itemDef.femaleEquip1 = 2778;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11527:
			itemDef.modelID = 2531;
			itemDef.name = "@gre@Lime @bla@whip";
			itemDef.description = "lime whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 2418;
			itemDef.femaleEquip1 = 2418;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11528:
			itemDef.modelID = 65209;
			itemDef.name = "@red@Patr@whi@iot @blu@whip";
			itemDef.description = "Patriot whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 2630;
			itemDef.femaleEquip1 = 2630;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 19810:
			itemDef.modelID = 60960;
			itemDef.name = "@red@Blood @whi@whip";
			itemDef.description = "Blood whip";
			itemDef.modelZoom = 840;
			itemDef.modelOffsetY = 56;
			itemDef.modelOffset1 = -2;
			itemDef.rotationY = 280;
			itemDef.maleEquip1 = 60957;
			itemDef.femaleEquip1 = 60957;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 928 };
			itemDef.editedModelColor = new int[] { 64434 };
			break;
		case 11530:
			itemDef.modelID = 24242;
			itemDef.name = "Patriot spirit shield";
			itemDef.description = "It's a patriot spirit shield";
			itemDef.modelZoom = 1616;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 1050;
			itemDef.rotationY = 396;
			itemDef.maleEquip1 = 2810;
			itemDef.femaleEquip1 = 2810;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 11531:
			itemDef.modelID = 33334;
			itemDef.name = "Colorful spirit shield";
			itemDef.description = "It's a colorful spirit shield";
			itemDef.modelZoom = 1616;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 1050;
			itemDef.rotationY = 396;
			itemDef.maleEquip1 = 62099;
			itemDef.femaleEquip1 = 62099;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 11532:
			itemDef.modelID = 2562;
			itemDef.name = "Rasta spirit shield";
			itemDef.description = "It's a rasta spirit shield";
			itemDef.modelZoom = 1616;
			itemDef.modelOffsetY = 4;
			itemDef.modelOffset1 = -3;
			itemDef.rotationX = 1050;
			itemDef.rotationY = 396;
			itemDef.maleEquip1 = 3021;
			itemDef.femaleEquip1 = 3021;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 8845:
			itemDef.modelID = 15335;
			itemDef.name = "Starter defender";
			itemDef.description = "Ice defender";
			itemDef.modelZoom = 490;
			itemDef.modelOffsetY = -30;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 1882;
			itemDef.rotationY = 221;
			itemDef.maleEquip1 = 15413;
			itemDef.femaleEquip1 = 15413;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 28, 74 };
			break;
		case 1067:
			itemDef.modelID = 2582;
			itemDef.name = "Starter Platelegs";
			itemDef.description = "platelegs";
			itemDef.modelZoom = 1740;
			itemDef.modelOffsetY = -8;
			itemDef.rotationY = 444;
			itemDef.maleEquip1 = 268;
			itemDef.femaleEquip1 = 432;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 6000, 434 };
			itemDef.editedModelColor = new int[] { 61, 41, 57 };
			break;
		case 1115:
			itemDef.modelID = 2605;
			itemDef.name = "Starter Platebody";
			itemDef.description = "platebody";
			itemDef.modelZoom = 1250;
			itemDef.modelOffset1 = -1;
			itemDef.rotationY = 488;
			itemDef.maleEquip1 = 306;
			itemDef.femaleEquip1 = 468;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 6000, 434 };
			itemDef.editedModelColor = new int[] { 24, 61, 41 };
			break;
		case 1153:
			itemDef.modelID = 2813;
			itemDef.name = "Starter Full Helm";
			itemDef.description = "full helm";
			itemDef.modelZoom = 800;
			itemDef.modelOffsetY = 6;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 152;
			itemDef.rotationY = 160;
			itemDef.maleEquip1 = 218;
			itemDef.femaleEquip1 = 394;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 6000, 434 };
			itemDef.editedModelColor = new int[] { 61, 926 };
			break;
		case 4121:
			itemDef.modelID = 5037;
			itemDef.name = "Starter Boots";
			itemDef.description = "boots";
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 4954;
			itemDef.femaleEquip1 = 5031;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 6000, 434 };
			itemDef.editedModelColor = new int[] { 5648, 5400, 61 };
			break;
		case 1191:
			itemDef.modelID = 2339;
			itemDef.name = "Starter Kiteshield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1560;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1104;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 486;
			itemDef.femaleEquip1 = 486;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 6000, 434 };
			itemDef.editedModelColor = new int[] { 7054, 61, 57 };
			break;
		case 1203:
			itemDef.modelID = 2672;
			itemDef.name = "Starter Dagger";
			itemDef.description = " dagger";
			itemDef.modelZoom = 760;
			itemDef.modelOffsetY = -4;
			itemDef.rotationX = 1276;
			itemDef.rotationY = 472;
			itemDef.maleEquip1 = 533;
			itemDef.femaleEquip1 = 533;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 61, 37, 74, 103 };
			break;
		case 1267:
			itemDef.modelID = 2529;
			itemDef.name = "Starter Pickaxe";
			itemDef.description = " pickaxe";
			itemDef.modelZoom = 1382;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 997;
			itemDef.rotationY = 660;
			itemDef.maleEquip1 = 509;
			itemDef.femaleEquip1 = 509;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000, 6000, 6000, 6000, 6000, 32558 };
			itemDef.editedModelColor = new int[] { 61, 51, 76, 74, 60, 121, 99, 42, 41 };
			break;
		case 1349:
			itemDef.modelID = 2544;
			itemDef.name = "Starter Hatchet";
			itemDef.description = " hatchet";
			itemDef.modelZoom = 1060;
			itemDef.modelOffset1 = -15;
			itemDef.rotationX = 1196;
			itemDef.rotationY = 520;
			itemDef.maleEquip1 = 510;
			itemDef.femaleEquip1 = 510;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 440:
			itemDef.modelID = 2748;
			itemDef.name = "Starter ore";
			itemDef.description = "ore";
			itemDef.modelZoom = 1400;
			itemDef.modelOffsetY = 15;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1576;
			itemDef.rotationY = 368;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 7062, 45 };
			break;
		case 2351:
			itemDef.modelID = 2408;
			itemDef.name = "Starter bar";
			itemDef.description = "Bar";
			itemDef.modelZoom = 820;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 1180;
			itemDef.rotationY = 196;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 7062 };
			break;
		case 9177:
			itemDef.modelID = 16876;
			itemDef.name = "Starter crossbow";
			itemDef.description = " crossbow";
			itemDef.modelZoom = 850;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 269;
			itemDef.rotationY = 323;
			itemDef.maleEquip1 = 16846;
			itemDef.femaleEquip1 = 16846;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 434, 434, 434, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 6447, 6443, 6439, 5656, 5652, 5904 };
			break;
		case 3192:
			itemDef.modelID = 2791;
			itemDef.name = "Starter halberd";
			itemDef.description = "Ice halberd";
			itemDef.modelZoom = 2130;
			itemDef.rotationX = 48;
			itemDef.rotationY = 604;
			itemDef.maleEquip1 = 555;
			itemDef.femaleEquip1 = 555;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 3096:
			itemDef.modelID = 3781;
			itemDef.name = "Starter claws";
			itemDef.description = "claws";
			itemDef.modelZoom = 630;
			itemDef.modelOffsetY = -13;
			itemDef.modelOffset1 = -7;
			itemDef.rotationX = 1340;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 3780;
			itemDef.femaleEquip1 = 42331;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 24, 33, 41, 49 };
			break;
		case 1420:
			itemDef.modelID = 2464;
			itemDef.name = "Starter mace";
			itemDef.description = "mace";
			itemDef.modelZoom = 1030;
			itemDef.modelOffsetY = -1;
			itemDef.rotationX = 1188;
			itemDef.rotationY = 392;
			itemDef.maleEquip1 = 502;
			itemDef.femaleEquip1 = 502;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1335:
			itemDef.modelID = 2731;
			itemDef.name = "Starter warhammer";
			itemDef.description = "warhammer";
			itemDef.modelZoom = 1050;
			itemDef.modelOffsetY = 3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 456;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 542;
			itemDef.femaleEquip1 = 542;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1309:
			itemDef.modelID = 2754;
			itemDef.name = "Starter 2h sword";
			itemDef.description = "2h sword";
			itemDef.modelZoom = 1711;
			itemDef.rotationX = 1513;
			itemDef.rotationY = 354;
			itemDef.maleEquip1 = 546;
			itemDef.femaleEquip1 = 546;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 75, 41, 127, 82, 48, 57 };
			break;
		case 1175:
			itemDef.modelID = 2720;
			itemDef.name = "Starter sq shield";
			itemDef.description = "sq shield";
			itemDef.modelZoom = 1410;
			itemDef.modelOffsetY = 174;
			itemDef.modelOffset1 = 2;
			itemDef.rotationX = 60;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 541;
			itemDef.femaleEquip1 = 541;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 57 };
			break;
		case 807:
			itemDef.modelID = 2379;
			itemDef.name = "Starter dart";
			itemDef.description = "dart";
			itemDef.modelZoom = 720;
			itemDef.modelOffsetY = 11;
			itemDef.modelOffset1 = 8;
			itemDef.rotationX = 336;
			itemDef.rotationY = 396;
			itemDef.maleEquip1 = 492;
			itemDef.femaleEquip1 = 492;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 82 };
			itemDef.stackable = true;
			break;
		case 826:
			itemDef.modelID = 2763;
			itemDef.name = "Starter javelin";
			itemDef.description = "javelin";
			itemDef.modelZoom = 1470;
			itemDef.modelOffsetY = 63;
			itemDef.modelOffset1 = -2;
			itemDef.rotationX = 1964;
			itemDef.rotationY = 268;
			itemDef.maleEquip1 = 547;
			itemDef.femaleEquip1 = 547;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 61, 22451 };
			itemDef.stackable = true;
			break;
		case 863:
			itemDef.modelID = 2557;
			itemDef.name = "Starter knife";
			itemDef.description = "knife";
			itemDef.modelZoom = 1080;
			itemDef.modelOffsetX = 1860;
			itemDef.modelOffsetY = -26;
			itemDef.modelOffset1 = -1;
			itemDef.rotationX = 112;
			itemDef.rotationY = 204;
			itemDef.maleEquip1 = 548;
			itemDef.femaleEquip1 = 548;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 61, 57 };
			itemDef.stackable = true;
			break;
		case 1239:
			itemDef.modelID = 2719;
			itemDef.name = "Starter spear";
			itemDef.description = "spear";
			itemDef.modelZoom = 1490;
			itemDef.modelOffsetX = 144;
			itemDef.modelOffsetY = -4;
			itemDef.modelOffset1 = 7;
			itemDef.rotationX = 112;
			itemDef.rotationY = 1960;
			itemDef.maleEquip1 = 540;
			itemDef.femaleEquip1 = 540;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 33, 28 };
			break;
		case 801:
			itemDef.modelID = 2752;
			itemDef.name = "Starter thrownaxe";
			itemDef.description = "thrownaxe";
			itemDef.modelZoom = 750;
			itemDef.modelOffsetY = 6;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 208;
			itemDef.rotationY = 388;
			itemDef.maleEquip1 = 545;
			itemDef.femaleEquip1 = 545;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000 };
			itemDef.editedModelColor = new int[] { 61, 57 };
			itemDef.stackable = true;
			break;
		case 1363:
			itemDef.modelID = 2778;
			itemDef.name = "Starter battleaxe";
			itemDef.description = "battleaxe";
			itemDef.modelZoom = 1180;
			itemDef.modelOffsetY = -2;
			itemDef.modelOffset1 = -7;
			itemDef.rotationX = 1128;
			itemDef.rotationY = 420;
			itemDef.maleEquip1 = 550;
			itemDef.femaleEquip1 = 550;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000 };
			itemDef.editedModelColor = new int[] { 61 };
			break;
		case 1279:
			itemDef.modelID = 2604;
			itemDef.name = "Starter sword";
			itemDef.description = "sword";
			itemDef.modelZoom = 1513;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1562;
			itemDef.maleEquip1 = 519;
			itemDef.femaleEquip1 = 519;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.newModelColor = new int[] { 6000, 6000, 6000, 6000, 6000, 6000 };
			itemDef.editedModelColor = new int[] { 127, 82, 75, 41, 48, 57 };
			break;
		/*
		 * from here forward item models do not work.
		 */
		/*
		 * case 13101: itemDef.modelID = 33314; itemDef.name = "Patriot top hat";
		 * itemDef.description = "Top hat"; itemDef.modelZoom = 659;
		 * itemDef.modelOffsetY = -3; itemDef.modelOffset1 = -3; itemDef.rotationX =
		 * 1893; itemDef.rotationY = 80; itemDef.maleEquip1 = 33336;
		 * itemDef.femaleEquip1 = 33336; itemDef.groundActions = new String[]
		 * {null,null,"Take",null,null}; itemDef.actions = new String[]
		 * {null,"Wear",null,null,"Drop"}; break; case 1333: itemDef.modelID = 48105;
		 * itemDef.name = "Trimmed scimitar"; itemDef.description = "Trimmed scimitar";
		 * itemDef.modelZoom = 1513; itemDef.modelOffsetY = -3; itemDef.modelOffset1 =
		 * 4; itemDef.rotationX = 40; itemDef.rotationY = 525; itemDef.maleEquip1 =
		 * 48112; itemDef.femaleEquip1 = 48112; itemDef.groundActions = new String[]
		 * {null,null,"Take",null,null}; itemDef.actions = new String[]
		 * {null,"Wield",null,null,"Drop"}; break; case 1291: itemDef.modelID = 6033;
		 * itemDef.name = "Lime longsword"; itemDef.description = "Lime longsword";
		 * itemDef.modelZoom = 1645; itemDef.modelOffsetX = 108; itemDef.rotationX =
		 * 1778; itemDef.rotationY = 1535; itemDef.maleEquip1 = 62348;
		 * itemDef.femaleEquip1 = 62348; itemDef.groundActions = new String[]
		 * {null,null,"Take",null,null}; itemDef.actions = new String[]
		 * {null,"Wield",null,null,"Drop"}; break; case 14099: itemDef.modelID = 43166;
		 * itemDef.name = "Golden pickaxe"; itemDef.description = "Golden pickaxe";
		 * itemDef.modelZoom = 1382; itemDef.modelOffsetY = 1; itemDef.modelOffset1 =
		 * -6; itemDef.rotationX = 997; itemDef.rotationY = 660; itemDef.maleEquip1 =
		 * 43414; itemDef.femaleEquip1 = 43414; itemDef.groundActions = new String[]
		 * {null,null,"Take",null,null}; itemDef.actions = new String[]
		 * {null,"Wield",null,null,"Drop"}; break; case 1237: itemDef.modelID = 54535;
		 * itemDef.name = "Lime spear"; itemDef.description = "Lime spear";
		 * itemDef.modelZoom = 1490; itemDef.modelOffsetX = 144; itemDef.modelOffsetY =
		 * -4; itemDef.modelOffset1 = 7; itemDef.rotationX = 112; itemDef.rotationY =
		 * 1960; itemDef.maleEquip1 = 56212; itemDef.femaleEquip1 = 56212;
		 * itemDef.groundActions = new String[] {null,null,"Take",null,null};
		 * itemDef.actions = new String[] {null,"Wield",null,null,"Drop"}; break; case
		 * 15241: itemDef.modelID = 48512; itemDef.name = "Minigun"; itemDef.description
		 * = "Minigun"; itemDef.modelZoom = 1579; itemDef.modelOffsetY = 11;
		 * itemDef.modelOffset1 = -13; itemDef.rotationX = 1805; itemDef.rotationY =
		 * 539; itemDef.maleEquip1 = 48465; itemDef.femaleEquip1 = 48465;
		 * itemDef.groundActions = new String[] {null,null,"Take",null,null};
		 * itemDef.actions = new String[] {null,"Wield",null,null,"Drop"};
		 * itemDef.newModelColor = new int[] {1}; itemDef.editedModelColor = new int[]
		 * {0}; break; case 6731: itemDef.modelID = 9932; itemDef.name =
		 * "Ring of Magic"; itemDef.description = "Ring of Magic"; break; case 6733:
		 * itemDef.modelID = 9930; itemDef.name = "Ring of Archery"; itemDef.description
		 * = "Archers' ring"; break; case 6735: itemDef.modelID = 9933; itemDef.name =
		 * "Ring of Knights"; itemDef.description = "Ring of Knights"; break; case 6737:
		 * itemDef.modelID = 9931; itemDef.name = "Ring of Rage"; itemDef.description =
		 * "Rage ring"; break; case 13896: itemDef.modelID = 42596; itemDef.name =
		 * "Sirenic full helm"; itemDef.description = "Sirenic full helm";
		 * itemDef.modelZoom = 800; itemDef.modelOffsetY = 6; itemDef.modelOffset1 = -1;
		 * itemDef.rotationX = 152; itemDef.rotationY = 160; itemDef.maleEquip1 = 42639;
		 * itemDef.femaleEquip1 = 42655; itemDef.groundActions = new String[]
		 * {null,null,"Take",null,null}; itemDef.actions = new String[]
		 * {null,"Wear",null,null,"Drop"}; break; case 13884: itemDef.modelID = 42602;
		 * itemDef.name = "Sirenic platebody"; itemDef.description =
		 * "Sirenic platebody"; itemDef.modelZoom = 1250; itemDef.modelOffset1 = -1;
		 * itemDef.modelOffsetY = 6; itemDef.rotationY = 488; itemDef.maleEquip1 =
		 * 42625; itemDef.femaleEquip1 = 42641;
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		/*
		 * END of models not working
		 */
		case 15441:
			itemDef.name = "Abbysal whip (Y)";
			break;
		case 15442:
			itemDef.name = "Abbysal whip (B)";
			break;
		case 15443:
			itemDef.name = "Abbysal whip (W)";
			break;
		case 15444:
			itemDef.name = "Abbysal whip (G)";
			break;
		case 21084:
			itemDef.modelID = 55070;
			itemDef.name = "Yellow Dfs";
			itemDef.description = "Dragonfire shield";
			itemDef.modelZoom = 2022;
			itemDef.rotationX = 123;
			itemDef.rotationY = 540;
			itemDef.maleEquip1 = 55069;
			itemDef.femaleEquip1 = 55069;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 21083:
			itemDef.modelID = 55068;
			itemDef.name = "Cyan Dfs";
			itemDef.description = "Dragonfire shield";
			itemDef.modelZoom = 2022;
			itemDef.rotationX = 123;
			itemDef.rotationY = 540;
			itemDef.maleEquip1 = 55067;
			itemDef.femaleEquip1 = 55067;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 21082:
			itemDef.modelID = 55066;
			itemDef.name = "Red Dfs";
			itemDef.description = "Dragonfire shield";
			itemDef.modelZoom = 2022;
			itemDef.rotationX = 123;
			itemDef.rotationY = 540;
			itemDef.maleEquip1 = 55065;
			itemDef.femaleEquip1 = 55065;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 17690:
			itemDef.modelID = 24142;
			itemDef.name = "Diamond scimitar";
			itemDef.description = "Diamond scimitar";
			itemDef.modelZoom = 1513;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 4;
			itemDef.rotationX = 40;
			itemDef.rotationY = 525;
			itemDef.maleEquip1 = 24052;
			itemDef.femaleEquip1 = 24052;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 21081:
			itemDef.modelID = 26424; // 65210
			itemDef.name = "Blue dragon staff";
			itemDef.description = "Staff of blue dragon";
			itemDef.modelZoom = 1490;
			itemDef.modelOffsetY = 2;
			itemDef.modelOffset1 = -5;
			itemDef.rotationX = 1400;
			itemDef.rotationY = 148;
			itemDef.maleEquip1 = 26447;
			itemDef.femaleEquip1 = 26447; // done
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		// case 21079:
		// itemDef.modelID = 179; //done
		// itemDef.name = "Fighter torso (L)";
		// itemDef.description = "Lime fighter torso";
		// itemDef.modelZoom = 1178;
		// itemDef.modelOffsetY = 4;
		// itemDef.rotationY = 606;
		// itemDef.maleEquip1 = 2649;
		// itemDef.femaleEquip1 = 2649; //done
		// itemDef.groundActions = new String[] { null, null, "Take", null, null };
		// itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
		// itemDef.newModelColor = new int[] { 1 };
		// itemDef.editedModelColor = new int[] { 0 };
		// break;
		// case 21078:
		// itemDef.modelID = 53327; //done
		// itemDef.name = "Fighter torso (P)";
		// itemDef.description = "Purple fighter torso";
		// itemDef.modelZoom = 1178;
		// itemDef.modelOffsetY = 4;
		// itemDef.rotationY = 606;
		// itemDef.maleEquip1 = 53835;
		// itemDef.femaleEquip1 = 53835; //done
		// itemDef.groundActions = new String[] { null, null, "Take", null, null };
		// itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
		// break;
		case 21077:
			itemDef.modelID = 42632; // done
			itemDef.name = "Dual Rapier";
			itemDef.description = "Dual Rapier";
			itemDef.modelZoom = 850;
			itemDef.modelOffsetY = -9;
			itemDef.modelOffset1 = 5;
			itemDef.rotationX = 1212;
			itemDef.rotationY = 272;
			itemDef.maleEquip1 = 42590;
			itemDef.femaleEquip1 = 42590;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 11586:
			itemDef.name = "White partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			break;
		case 11587:
			itemDef.name = "Orange partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			itemDef.newModelColor = new int[] { 5055, 5055 };
			itemDef.editedModelColor = new int[] { 13549, 15472 };
			break;
		case 11588:
			itemDef.name = "Cyan partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			itemDef.editedModelColor = new int[] { 13549, 15472 };
			itemDef.newModelColor = new int[] { 32703, 32703 };
			break;
		case 11589:
			itemDef.name = "Purple partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			itemDef.editedModelColor = new int[] { 13549, 15472 };
			itemDef.newModelColor = new int[] { 52127, 52127 };
			break;
		case 11590:
			itemDef.name = "Lime partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			itemDef.editedModelColor = new int[] { 13549, 15472 };
			itemDef.newModelColor = new int[] { 22463, 22463 };
			break;
		case 11591:
			itemDef.name = "Pink partyhat & specs";
			itemDef.modelZoom = 575;
			itemDef.rotationY = 121;
			itemDef.rotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 1;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.modelID = 42323; // done
			itemDef.maleEquip1 = 42322; // done
			itemDef.femaleEquip1 = 42322; // done
			itemDef.editedModelColor = new int[] { 13549, 15472 };
			itemDef.newModelColor = new int[] { 54207, 54207 };
			break;
		case 11592:
			itemDef.modelID = 65504;
			itemDef.name = "Malevolent 2H Sword";
			itemDef.description = "It's a Malevolent 2H Sword.";
			itemDef.modelZoom = 1957;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 498;
			itemDef.rotationY = 444;
			itemDef.maleEquip1 = 65204; // done
			itemDef.femaleEquip1 = 65204;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11593:
			itemDef.modelID = 65201;
			itemDef.name = "Malevolent Guard";
			itemDef.description = "It's a Malevolent Guard.";
			itemDef.modelZoom = 1560;
			itemDef.modelOffsetY = -14;
			itemDef.modelOffset1 = -6;
			itemDef.rotationX = 1104;
			itemDef.rotationY = 344;
			itemDef.maleEquip1 = 65505; // done
			itemDef.femaleEquip1 = 65505;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

			case 21070:
				itemDef.modelID = 65201;
				itemDef.name = "Tormented Guard";
				itemDef.description = "It's a Tormented Guard.";
				itemDef.modelZoom = 1560;
				itemDef.modelOffsetY = -14;
				itemDef.colourRedefine3 = 6500;
				itemDef.modelOffset1 = -6;
				itemDef.rotationX = 1104;
				itemDef.rotationY = 344;
				itemDef.maleEquip1 = 65505; // done
				itemDef.femaleEquip1 = 65505;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
		case 11594:
			itemDef.modelID = 65492;
			itemDef.name = "Malevolent Sword";
			itemDef.description = "It's a Malevolent Sword.";
			itemDef.modelZoom = 1645;
			itemDef.modelOffsetX = 108;
			itemDef.rotationX = 1778;
			itemDef.rotationY = 1535;
			itemDef.maleEquip1 = 65491;
			itemDef.femaleEquip1 = 65491;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11595:
			itemDef.modelID = 65493;
			itemDef.name = "Malevolent Maul";
			itemDef.description = "It's a Malevolent Maul.";
			itemDef.modelZoom = 1957;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 1;
			itemDef.rotationX = 498;
			itemDef.rotationY = 444;
			itemDef.maleEquip1 = 65494;
			itemDef.femaleEquip1 = 65494;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11596:
			itemDef.name = "Malevolent Gloves";
			itemDef.description = "It's a pair of Malevolent gloves.";
			itemDef.modelZoom = 930;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 8;
			itemDef.rotationX = 828;
			itemDef.rotationY = 420;
			itemDef.modelID = 65202; // 65495;
			itemDef.maleEquip1 = 65496;
			itemDef.femaleEquip1 = 65496;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 21071:
			itemDef.name = "Tormented Gloves";
			itemDef.description = "It's a pair of Tormented gloves.";
			itemDef.modelZoom = 930;
			itemDef.modelOffsetY = -8;
			itemDef.modelOffset1 = 8;
			itemDef.colourRedefine3 = 6500;
			itemDef.rotationX = 828;
			itemDef.rotationY = 420;
			itemDef.modelID = 65202; // 65495;
			itemDef.maleEquip1 = 65496;
			itemDef.femaleEquip1 = 65496;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
			case 773:
				itemDef.name = "Melee K0 Ring";
				itemDef.colourRedefine3 = 180000;
				break;
		case 11597:
			itemDef.name = "Malevolent Boots";
			itemDef.description = "It's a pair of Malevolent boots.";
			itemDef.modelID = 65497;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 65497;
			itemDef.femaleEquip1 = 65497;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 21073:
			itemDef.name = "Tormented Boots";
			itemDef.description = "It's a pair of Tormented boots.";
			itemDef.modelID = 65497;
			itemDef.modelZoom = 770;
			itemDef.colourRedefine3 = 6500;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 65497;
			itemDef.femaleEquip1 = 65497;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;

		case 21074:
			itemDef.name = "Death Caller";
			itemDef.description = "It's a pair of Tormented boots.";
			itemDef.modelID = 33441;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 33442;
			itemDef.femaleEquip1 = 33442;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;

		case 21075:
			itemDef.name = "Scythe 2";
			itemDef.description = "It's a pair of Tormented boots.";
			itemDef.modelID = 33443;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 33444;
			itemDef.femaleEquip1 = 33444;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 21076:
			itemDef.name = "Scythe 3";
			itemDef.description = "It's a pair of Tormented boots.";
			itemDef.modelID = 33445;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 33446;
			itemDef.femaleEquip1 = 33446;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 21078:
			itemDef.name = "Scythe 4";
			itemDef.description = "It's a pair of Tormented boots.";
			itemDef.modelID = 33447;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 33448;
			itemDef.femaleEquip1 = 33448;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;


		case 11598:
			itemDef.modelID = 65498;
			itemDef.name = "Malevolent Wings";
			itemDef.description = "It's a pair of Malevolent wings.";
			itemDef.modelZoom = 2130;
			itemDef.modelOffsetY = 12;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 992;
			itemDef.rotationY = 364;
			itemDef.maleEquip1 = 65499;
			itemDef.femaleEquip1 = 65499;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 21072:
			itemDef.modelID = 65498;
			itemDef.name = "Tormented Wings";
			itemDef.description = "It's a pair of Tormented wings.";
			itemDef.modelZoom = 2130;
			itemDef.modelOffsetY = 12;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 992;
			itemDef.colourRedefine3 = 6500;
			itemDef.rotationY = 364;
			itemDef.maleEquip1 = 65499;
			itemDef.femaleEquip1 = 65499;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 11599:
			itemDef.modelID = 65500;
			itemDef.name = "Sirenic Crossbow";
			itemDef.description = "It's a Sirenic Crossbow.";
			itemDef.modelZoom = 1200;
			itemDef.rotationX = 269;
			itemDef.rotationY = 323;
			itemDef.maleEquip1 = 65501;
			itemDef.femaleEquip1 = 65501;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11600:
			itemDef.modelID = 65502; // done
			itemDef.name = "Sirenic Buckler";
			itemDef.description = "It's a Sirenic Buckler.";
			itemDef.modelZoom = 625;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.maleEquip1 = 65470;
			itemDef.femaleEquip1 = 65470;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11601:
			itemDef.name = "Sirenic Gloves";
			itemDef.description = "It's a pair of Sirenic gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65471;
			itemDef.maleEquip1 = 65472;
			itemDef.femaleEquip1 = 65472;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11602:
			itemDef.name = "Sirenic Boots";
			itemDef.description = "It's a pair of Sirenic boots.";
			itemDef.modelID = 65473;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 65474;
			itemDef.femaleEquip1 = 65474;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 11603:
			itemDef.modelID = 65475; // done
			itemDef.name = "Sirenic Tendrils"; // wings
			itemDef.description = "It's a pair of Sirenic Tendrils.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65456;
			itemDef.femaleEquip1 = 65456;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11604:
			itemDef.modelID = 65439;
			itemDef.name = "Seismic Defender";
			itemDef.description = "It's a Seismic Defender.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65421;
			itemDef.femaleEquip1 = 65421;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11605:
			itemDef.modelID = 65423;
			itemDef.name = "Seismic Staff";
			itemDef.description = "It's a Seismic staff.";
			itemDef.modelZoom = 900;
			itemDef.rotationY = 324;
			itemDef.rotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffsetY = 3;
			itemDef.maleEquip1 = 65422;
			itemDef.femaleEquip1 = 65422;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11606:
			itemDef.name = "Tectonic Gloves";
			itemDef.description = "It's a pair of Tectonic gloves.";
			itemDef.modelZoom = 592;
			itemDef.rotationY = 323;
			itemDef.rotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = 5;
			itemDef.modelID = 65424;
			itemDef.maleEquip1 = 65425;
			itemDef.femaleEquip1 = 65425;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 11607:
			itemDef.name = "Tectonic Boots";
			itemDef.description = "It's a pair of Tectonic boots.";
			itemDef.modelID = 65426;
			itemDef.modelZoom = 770;
			itemDef.modelOffsetY = -3;
			itemDef.modelOffset1 = 3;
			itemDef.rotationX = 156;
			itemDef.rotationY = 164;
			itemDef.maleEquip1 = 65427;
			itemDef.femaleEquip1 = 65427;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 11608:
			itemDef.modelID = 65428; // done
			itemDef.name = "Tectonic Crystals"; // wings
			itemDef.description = "It's a pair of Tectonic Crystals.";
			itemDef.modelZoom = 1700;
			itemDef.rotationY = 500;
			itemDef.rotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.maleEquip1 = 65200;
			itemDef.femaleEquip1 = 65200;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		}
		return itemDef;

	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) spriteCache.get(i);
			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];
			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.getItemModelFinalised(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lentItemID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Rasterizer.textureInt1;
		int l1 = Rasterizer.textureInt2;
		int ai[] = Rasterizer.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Rasterizer.aBoolean1462 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Rasterizer.setDefaultBounds();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
		int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
		model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1,
				l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (sprite2.myPixels[i5 + j4 * 32] == 0)
					if (i5 > 0 && sprite2.myPixels[i5 - 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
		}
		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (sprite2.myPixels[j5 + k4 * 32] == 0)
						if (j5 > 0 && sprite2.myPixels[j5 - 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
							&& sprite2.myPixels[k5 - 1 + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lentItemID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			spriteCache.put(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Rasterizer.textureInt1 = k1;
		Rasterizer.textureInt2 = l1;
		Rasterizer.anIntArray1472 = ai;
		Rasterizer.aBoolean1462 = true;
		if (itemDef.stackable)
			sprite2.maxWidth = 33;
		else
			sprite2.maxWidth = 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public static Sprite getSprite(int i, int j, int k, int zoom) {
		if (k == 0 && zoom != -1) {
			Sprite sprite = (Sprite) spriteCache.get(i);
			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];

			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.getItemModelFinalised(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lendID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Rasterizer.textureInt1;
		int l1 = Rasterizer.textureInt2;
		int ai[] = Rasterizer.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Rasterizer.aBoolean1462 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Rasterizer.setDefaultBounds();
		int k3 = itemDef.modelZoom;
		if (zoom != -1 && zoom != 0)
			k3 = itemDef.modelZoom * 100 / zoom;
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
		int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
		model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1,
				l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--) {
				if (sprite2.myPixels[i5 + j4 * 32] != 0)
					continue;
				if (i5 > 0 && sprite2.myPixels[i5 - 1 + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
					sprite2.myPixels[i5 + j4 * 32] = 1;
			}

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--) {
					if (sprite2.myPixels[j5 + k4 * 32] != 0)
						continue;
					if (j5 > 0 && sprite2.myPixels[j5 - 1 + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
						sprite2.myPixels[j5 + k4 * 32] = k;
				}

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
							&& sprite2.myPixels[k5 - 1 + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;

			}

		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lendID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			spriteCache.put(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Rasterizer.textureInt1 = k1;
		Rasterizer.textureInt2 = l1;
		Rasterizer.anIntArray1472 = ai;
		Rasterizer.aBoolean1462 = true;
		sprite2.maxWidth = itemDef.stackable ? 33 : 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public static void nullLoader() {
		modelCache = null;
		spriteCache = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public static void setSettings() {
		try {
			prices = new int[22694];
			int index = 0;
			for (String line : Files.readAllLines(Paths.get(signlink.findcachedir() + "data.txt"))) {
				prices[index] = Integer.parseInt(line);
				index++;
			}
			for (int i : UNTRADEABLE_ITEMS) {
				untradeableItems.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unpackConfig(CacheArchive streamLoader) {
		stream = new Stream(streamLoader.getDataForName("obj.dat"));
		Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 1000];
		int i = 2;
		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ItemDef[10];
		for (int k = 0; k < 10; k++)
			cache[k] = new ItemDef();
		setSettings();
	}

	public byte femaleYOffset;

	public int value;

	public int[] editedModelColor;

	public int id;

	public int[] newModelColor;
	public boolean membersObject;
	public int femaleEquip3;
	public int certTemplateID;
	public int femaleEquip2;
	public int maleEquip1;
	public int maleDialogueModel;
	public int sizeX;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public int femaleDialogueModel;
	public int modelID;
	public int maleDialogue;
	public boolean stackable;
	public String description;
	public int certID;
	public int modelZoom;
	public int lightness;
	public int maleEquip3;
	public int maleEquip2;
	public String actions[];
	public int rotationY;
	public int sizeZ;
	public int sizeY;
	public int[] stackIDs;
	public int modelOffsetY;
	public int shadow;
	public int femaleDialogue;
	public int rotationX;
	public int femaleEquip1;
	public int[] stackAmounts;
	public int team;
	public int modelOffsetX;
	public byte maleYOffset;
	public byte maleXOffset;
	public int lendID;
	public int lentItemID;
	public boolean untradeable;

	public ItemDef() {
		id = -1;
	}

	public boolean dialogueModelFetched(int j) {
		int k = maleDialogue;
		int l = maleDialogueModel;
		if (j == 1) {
			k = femaleDialogue;
			l = femaleDialogueModel;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.modelIsFetched(k))
			flag = false;
		if (l != -1 && !Model.modelIsFetched(l))
			flag = false;
		return flag;
	}

	public boolean equipModelFetched(int gender) {
		int fistModel = maleEquip1;
		int secondModel = maleEquip2;
		int thirdModel = maleEquip3;
		if (gender == 1) {
			fistModel = femaleEquip1;
			secondModel = femaleEquip2;
			thirdModel = femaleEquip3;
		}
		if (fistModel == -1)
			return true;
		boolean flag = true;
		if (!Model.modelIsFetched(fistModel))
			flag = false;
		if (secondModel != -1 && !Model.modelIsFetched(secondModel))
			flag = false;
		if (thirdModel != -1 && !Model.modelIsFetched(thirdModel))
			flag = false;
		return flag;
	}

	public Model getDialogueModel(int gender) {
		int k = maleDialogue;
		int l = maleDialogueModel;
		if (gender == 1) {
			k = femaleDialogue;
			l = femaleDialogueModel;
		}
		if (k == -1)
			return null;
		Model model = Model.fetchModel(k);
		if (l != -1) {
			Model model_1 = Model.fetchModel(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.recolour(editedModelColor[i1], newModelColor[i1]);
		}
		if (colourRedefine > 0)
			model.method1337(colourRedefine);
		if (colourRedefine2 != 0)
			model.method1338(colourRedefine2);
		if (colourRedefine3 != 0)
			model.method1339(colourRedefine3);
		return model;
	}

	public Model getEquipModel(int gender) {
		int j = maleEquip1;
		int k = maleEquip2;
		int l = maleEquip3;
		if (gender == 1) {
			j = femaleEquip1;
			k = femaleEquip2;
			l = femaleEquip3;
		}
		if (j == -1)
			return null;
		Model model = Model.fetchModel(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.fetchModel(k);
				Model model_3 = Model.fetchModel(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.fetchModel(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		if (j == 62367)
			model.translate(68, 7, -8);
		else if (gender == 0 && maleYOffset != 0)
			model.translate(0, maleYOffset, 0);
		else if (gender == 1 && femaleYOffset != 0)
			model.translate(0, femaleYOffset, 0);
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.recolour(editedModelColor[i1], newModelColor[i1]);
		}
		if (colourRedefine > 0)
			model.method1337(colourRedefine);
		if (colourRedefine2 != 0)
			model.method1338(colourRedefine2);
		if (colourRedefine3 != 0)
			model.method1339(colourRedefine3);
		return model;
	}

	public Model getItemModel(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).getItemModel(1);
		}
		Model model = Model.fetchModel(modelID);
		if (model == null)
			return null;
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.recolour(editedModelColor[l], newModelColor[l]);
		}
		if (colourRedefine > 0)
			model.method1337(colourRedefine);
		if (colourRedefine2 != 0)
			model.method1338(colourRedefine2);
		if (colourRedefine3 != 0)
			model.method1339(colourRedefine3);
		return model;
	}

	public Model getItemModelFinalised(int amount) {
		if (stackIDs != null && amount > 1) {
			int stackId = -1;
			for (int k = 0; k < 10; k++)
				if (amount >= stackAmounts[k] && stackAmounts[k] != 0)
					stackId = stackIDs[k];
			if (stackId != -1)
				return forID(stackId).getItemModelFinalised(1);
		}
		Model model = (Model) modelCache.get(id);
		if (model != null)
			return model;
		model = Model.fetchModel(modelID);
		if (model == null)
			return null;
		if (sizeX != 128 || sizeY != 128 || sizeZ != 128)
			model.scaleT(sizeX, sizeZ, sizeY);
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.recolour(editedModelColor[l], newModelColor[l]);
		}
		if (colourRedefine > 0)
			model.method1337(colourRedefine);
		if (colourRedefine2 != 0)
			model.method1338(colourRedefine2);
		if (colourRedefine3 != 0)
			model.method1339(colourRedefine3);
		model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
		model.rendersWithinOneTile = true;
		modelCache.put(model, id);
		return model;
	}

	private void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				modelID = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readString();
			else if (i == 4)
				modelZoom = stream.readUnsignedWord();
			else if (i == 5)
				rotationY = stream.readUnsignedWord();
			else if (i == 6)
				rotationX = stream.readUnsignedWord();
			else if (i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (i == 8) {
				modelOffsetY = stream.readUnsignedWord();
				if (modelOffsetY > 32767)
					modelOffsetY -= 0x10000;
			} else if (i == 10)
				stream.readUnsignedWord();
			else if (i == 11)
				stackable = true;
			else if (i == 12)
				value = stream.readUnsignedWord();
			else if (i == 16)
				membersObject = true;
			else if (i == 23) {
				maleEquip1 = stream.readUnsignedWord();
				maleYOffset = stream.readSignedByte();
			} else if (i == 24)
				maleEquip2 = stream.readUnsignedWord();
			else if (i == 25) {
				femaleEquip1 = stream.readUnsignedWord();
				femaleYOffset = stream.readSignedByte();
			} else if (i == 26)
				femaleEquip2 = stream.readUnsignedWord();
			else if (i >= 30 && i < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if (groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if (i >= 35 && i < 40) {
				if (actions == null)
					actions = new String[5];
				actions[i - 35] = stream.readString();
				if (actions[i - 35].equalsIgnoreCase("null"))
					actions[i - 35] = null;
			} else if (i == 40) {
				int j = stream.readUnsignedByte();
				editedModelColor = new int[j];
				newModelColor = new int[j];
				for (int k = 0; k < j; k++) {
					editedModelColor[k] = stream.readUnsignedWord();
					newModelColor[k] = stream.readUnsignedWord();
				}
			} else if (i == 78)
				maleEquip3 = stream.readUnsignedWord();
			else if (i == 79)
				femaleEquip3 = stream.readUnsignedWord();
			else if (i == 90)
				maleDialogue = stream.readUnsignedWord();
			else if (i == 91)
				femaleDialogue = stream.readUnsignedWord();
			else if (i == 92)
				maleDialogueModel = stream.readUnsignedWord();
			else if (i == 93)
				femaleDialogueModel = stream.readUnsignedWord();
			else if (i == 95)
				modelOffsetX = stream.readUnsignedWord();
			else if (i == 97)
				certID = stream.readUnsignedWord();
			else if (i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if (i >= 100 && i < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if (i == 110)
				sizeX = stream.readUnsignedWord();
			else if (i == 111)
				sizeY = stream.readUnsignedWord();
			else if (i == 112)
				sizeZ = stream.readUnsignedWord();
			else if (i == 113)
				shadow = stream.readSignedByte();
			else if (i == 114)
				lightness = stream.readSignedByte() * 5;
			else if (i == 115)
				team = stream.readUnsignedByte();
			else if (i == 116)
				lendID = stream.readUnsignedWord();
			else if (i == 117)
				lentItemID = stream.readUnsignedWord();
		} while (true);
	}

	public void setDefaults() {
		untradeable = false;
		modelID = 0;
		name = null;
		description = null;
		editedModelColor = null;
		newModelColor = null;
		modelZoom = 2000;
		rotationY = 0;
		rotationX = 0;
		modelOffsetX = 0;
		modelOffset1 = 0;
		modelOffsetY = 0;
		stackable = false;
		value = 0;
		membersObject = false;
		groundActions = null;
		actions = null;
		maleEquip1 = -1;
		maleEquip2 = -1;
		maleYOffset = 0;
		maleXOffset = 0;
		femaleEquip1 = -1;
		femaleEquip2 = -1;
		femaleYOffset = 0;
		maleEquip3 = -1;
		femaleEquip3 = -1;
		maleDialogue = -1;
		maleDialogueModel = -1;
		femaleDialogue = -1;
		femaleDialogueModel = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		sizeX = 128;
		sizeY = 128;
		sizeZ = 128;
		shadow = 0;
		lightness = 0;
		team = 0;
		lendID = -1;
		lentItemID = -1;
		colourRedefine = 0;
		colourRedefine2 = 0;
		colourRedefine3 = 0;
	}

	private void toLend() {
		ItemDef itemDef = forID(lentItemID);
		actions = new String[5];
		modelID = itemDef.modelID;
		modelOffset1 = itemDef.modelOffset1;
		rotationX = itemDef.rotationX;
		modelOffsetY = itemDef.modelOffsetY;
		modelZoom = itemDef.modelZoom;
		rotationY = itemDef.rotationY;
		modelOffsetX = itemDef.modelOffsetX;
		value = 0;
		ItemDef itemDef_1 = forID(lendID);
		maleDialogueModel = itemDef_1.maleDialogueModel;
		editedModelColor = itemDef_1.editedModelColor;
		maleEquip3 = itemDef_1.maleEquip3;
		maleEquip2 = itemDef_1.maleEquip2;
		femaleDialogueModel = itemDef_1.femaleDialogueModel;
		maleDialogue = itemDef_1.maleDialogue;
		groundActions = itemDef_1.groundActions;
		maleEquip1 = itemDef_1.maleEquip1;
		name = itemDef_1.name;
		femaleEquip1 = itemDef_1.femaleEquip1;
		membersObject = itemDef_1.membersObject;
		femaleDialogue = itemDef_1.femaleDialogue;
		femaleEquip2 = itemDef_1.femaleEquip2;
		femaleEquip3 = itemDef_1.femaleEquip3;
		newModelColor = itemDef_1.newModelColor;
		team = itemDef_1.team;
		if (itemDef_1.actions != null) {
			for (int i_33_ = 0; i_33_ < 4; i_33_++)
				actions[i_33_] = itemDef_1.actions[i_33_];
		}
		actions[4] = "Discard";
	}

	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelID = itemDef.modelID;
		modelZoom = itemDef.modelZoom;
		rotationY = itemDef.rotationY;
		rotationX = itemDef.rotationX;
		modelOffsetX = itemDef.modelOffsetX;
		modelOffset1 = itemDef.modelOffset1;
		modelOffsetY = itemDef.modelOffsetY;
		editedModelColor = itemDef.editedModelColor;
		newModelColor = itemDef.newModelColor;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = "Swap this note at any bank for " + s + " " + itemDef_1.name + ".";
		stackable = true;
	}
}