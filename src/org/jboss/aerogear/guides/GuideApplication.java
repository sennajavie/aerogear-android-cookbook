package org.jboss.aerogear.guides;

import android.app.Application;
import org.jboss.aerogear.android.DataManager;
import org.jboss.aerogear.android.impl.datamanager.MemoryStorage;
import org.jboss.aerogear.android.impl.datamanager.StoreConfig;
import org.jboss.aerogear.android.impl.datamanager.StoreTypes;
import org.jboss.aerogear.guides.datamanager.IncrementalIdGenerator;
import org.jboss.aerogear.guides.model.Car;

public class GuideApplication extends Application {

    private MemoryStorage memoryStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        createMemoryStorage();
    }

    private void createMemoryStorage() {

        DataManager dataManager = new DataManager();

        StoreConfig storeConfig = new StoreConfig();
        storeConfig.setContext(getApplicationContext());
        storeConfig.setType(StoreTypes.MEMORY);
        storeConfig.setKlass(Car.class);

        storeConfig.setIdGenerator(new IncrementalIdGenerator());

        memoryStorage = (MemoryStorage) dataManager.store("carStore", storeConfig);

        // Put inicial data
        memoryStorage.save(new Car("Porsche", "911", 135000));
        memoryStorage.save(new Car("Nissan", "GT-R", 80000));
        memoryStorage.save(new Car("BMW", "M3", 60500));
        memoryStorage.save(new Car("Audi", "S5", 53000));
        memoryStorage.save(new Car("Audi", "TT", 40000));

    }

    public MemoryStorage<Car> getMemoryStorage() {
        return memoryStorage;
    }

}
