package lykrast.sheikahfont;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(SheikahFont.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = SheikahFont.MODID)
public class SheikahFont {
	public static final String MODID = "sheikahfont";

	public SheikahFont() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(bus);
		PATTERNS.register(bus);
		bus.addListener(SheikahFont::putItemInTheTab);
	}

	private static final DeferredRegister<BannerPattern> PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	private static RegistryObject<Item> bannerPattern;
	private static final String[] CHARACTERS = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "dash", "dot", "exclamation", "question" };

	static {
		bannerPattern = ITEMS.register("banner_pattern_sheikah", () -> new BannerPatternItem(TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(MODID, "pattern_item/sheikah")), new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
		for (String s : CHARACTERS) {
			PATTERNS.register("sheikah_" + s, () -> new BannerPattern("sheikah_" + s));
		}
	}

	public static void putItemInTheTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) event.accept(bannerPattern);
	}
}
