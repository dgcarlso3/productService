package com.carlson.productservice.testdata;

import com.carlson.productservice.InventoryTypeEnum;
import com.carlson.productservice.media.Media;
import com.carlson.productservice.media.MediaRepository;
import com.carlson.productservice.sku.Sku;
import com.carlson.productservice.sku.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestDataService {

    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private MediaRepository mediaRepository;

    public TestDataService() {
    }

    public String makeTestSkus() {
        Sku sku1 = new Sku();
        sku1.setDescription("8 pound test");
        sku1.setProductid(19);
        sku1.setInventoryType(InventoryTypeEnum.CHECK_QUANTITY);
        sku1.setQuantityAvailable(42);
        sku1.setName("Fishing line");
        sku1.setRetailPrice(BigDecimal.valueOf(8.99));
        sku1.setSalePrice(BigDecimal.valueOf(8.99));

        skuRepository.save(sku1);

        sku1 = new Sku();
        sku1.setDescription("8 pound test");
        sku1.setProductid(19);
        sku1.setInventoryType(InventoryTypeEnum.CHECK_QUANTITY);
        sku1.setQuantityAvailable(42);
        sku1.setName("Fishing line");
        sku1.setRetailPrice(BigDecimal.valueOf(8.99));
        sku1.setSalePrice(BigDecimal.valueOf(8.99));

        skuRepository.save(sku1);

        return "Saved";
    }

    public String makeTestMedias() {
        Media media = new Media();
        media.setProductid(19);
        media.setURL("http://google.com");
        media.setAltText("whatever");
        mediaRepository.save(media);

        media = new Media();
        media.setProductid(19);
        media.setURL("http://foobar.com");
        media.setAltText("whatever2");
        mediaRepository.save(media);

        return "Saved";
    }
}
