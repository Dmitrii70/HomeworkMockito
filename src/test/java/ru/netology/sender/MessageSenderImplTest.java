package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

@Mock
private GeoService geoService;
@Mock
private LocalizationService localizationService;

@InjectMocks
private MessageSenderImpl messageSenderImpl;

    @Test
    void sendInRussia() {
        Location location = new Location("Moscow", Country.RUSSIA, "Green street", 1);

        when(geoService.byIp(anyString())).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Russia");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        String locale = messageSenderImpl.send(headers);

        assertEquals("Russia",locale);


    }
}