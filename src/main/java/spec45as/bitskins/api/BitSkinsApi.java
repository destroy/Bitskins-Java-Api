package spec45as.bitskins.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.LoggerFactory;
import spec45as.bitskins.error.UnsuccessfulRequestException;
import spec45as.bitskins.http.Http;
import spec45as.bitskins.http.TestingHttp;
import spec45as.bitskins.objects.*;
import spec45as.bitskins.objects.SearchEnums.SEARCH_OPTIONAL_CHOICE;
import spec45as.bitskins.objects.SearchEnums.SEARCH_SORTING_CHOICE;
import spec45as.bitskins.objects.SearchEnums.SEARCH_SORTING_DIRECTION;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


public class BitSkinsApi {

    static org.slf4j.Logger log = LoggerFactory.getLogger(BitSkinsApi.class);
    private ObjectMapper defaultObjectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private Http httpConnector;
    private String API_KEY;
    private String SECRET_KEY;


    public String getShopUrl() {
        return "https://bitskins.com/";
    }

    public BitSkinsApi(String apiKey, String secret, TestingHttp testConnector) {
        new BitSkinsApi(apiKey, secret);
        httpConnector = testConnector;
    }

    public BitSkinsApi(String apiKey, String secret) {
        this.API_KEY = apiKey;
        this.SECRET_KEY = secret;
        httpConnector = new Http();
        defaultObjectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    protected synchronized JsonNode getJsonResponse(String url) throws IOException {
        return httpConnector.getJsonResponse(url);
    }

    private boolean isRequestSuccessful(JsonNode jsonNode) throws UnsuccessfulRequestException {
        if (jsonNode.get("status") != null) {
            if (jsonNode.get("status").asText().equals("success")) {
                return true;
            } else if (jsonNode.get("status").asText().equals("fail")) {
                if (jsonNode.get("data") != null && jsonNode.get("data").get("error_message") != null) {
                    throw new UnsuccessfulRequestException(jsonNode.get("data").get("error_message").asText());
                }
            }
        }
        throw new UnsuccessfulRequestException("Request doesn't contain any status data");
    }

    /**
     * @return current wallet balance
     */
    public BitSkinsAccountBalance getAccountBalance() throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_account_balance/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        return defaultObjectMapper.readValue(jsonNode.get("data").toString(), BitSkinsAccountBalance.class);
    }

    /**
     * @return current bitskins prices
     */
    public HashMap<String, BitSkinsItemPrice> getAllItemsPrices() throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_all_item_prices/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        HashMap<String, BitSkinsItemPrice> priceHashMap = new HashMap<>();
        JsonNode prices = jsonNode.get("prices");
        Iterator<JsonNode> iterator = prices.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItemPrice bitSkinsPrice =
                    defaultObjectMapper.readValue(node.toString(), BitSkinsItemPrice.class);
            priceHashMap.put(bitSkinsPrice.getMarketHashName(), bitSkinsPrice);
        }

        return priceHashMap;
    }

    /**
     * @return current real bitskins prices, max 250
     */
    public HashMap<String, BitSkinsItemCurrentPrice> getAllRealItemsPrices(ArrayList<String> listOfNames) throws IOException,
            UnsuccessfulRequestException {
        StringJoiner joiner = new StringJoiner(",");

        for (String itemName : listOfNames) {
            joiner.add(itemName);
        }

        String url = getShopUrl().concat(
                String.format("api/v1/get_price_data_for_items_on_sale/?api_key=%s&code=%s&names=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), URLEncoder.encode(joiner.toString(),
                                "UTF-8").replaceAll("\\+", "%20")));

        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        HashMap<String, BitSkinsItemCurrentPrice> priceHashMap = new HashMap<>();
        JsonNode prices = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = prices.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItemCurrentPrice bitSkinsPrice =
                    defaultObjectMapper.readValue(node.toString(), BitSkinsItemCurrentPrice.class);
            priceHashMap.put(bitSkinsPrice.getMarketHashName(), bitSkinsPrice);
        }

        return priceHashMap;
    }

    /**
     * @return current bitskins buy orders
     */
    public HashMap<String, BitSkinsBuyOrder> getAllBuyOrdersPrices() throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/summarize_buy_orders/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<String, BitSkinsBuyOrder> bitSkinsBuyOrderHashMap = new HashMap<>();

        JsonNode items = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = items.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            String key = node.get(0).asText().intern();
            try {
                BitSkinsBuyOrder bitSkinsBuyOrder =
                        defaultObjectMapper.readValue(node.get(1).toString(), BitSkinsBuyOrder.class);
                bitSkinsBuyOrder.setItemName(key);
                bitSkinsBuyOrderHashMap.put(key, bitSkinsBuyOrder);
            } catch (Exception error) {
                log.error(error.getMessage(), error);
            }
        }

        return bitSkinsBuyOrderHashMap;
    }

    public List<BitSkinsItem> bitSkinsDefaultSearch(
            int pageNumber, String marketHashName) throws IOException, UnsuccessfulRequestException {
        return bitSkinsSearch(pageNumber,
                SEARCH_SORTING_CHOICE.SEARCH_SORTING_BY_PRICE,
                SEARCH_SORTING_DIRECTION.SEARCH_SORTING_ASC,
                marketHashName,
                0f, 9999999f,
                SEARCH_OPTIONAL_CHOICE.ANY,
                SEARCH_OPTIONAL_CHOICE.ANY);
    }

    /**
     * Page number. (optional)
     * {created_at|price|wear_value} (optional)
     * {desc|asc} (optional)
     * market_hash_name
     * min_price
     * max_price
     * has_stickers     {-1|0|1} (optional)
     * is_stattrak     {-1|0|1} (optional)
     *
     * @return bitSkins Search
     */
    public List<BitSkinsItem> bitSkinsSearch(
            int pageNumber,
            SEARCH_SORTING_CHOICE searchChoice,
            SEARCH_SORTING_DIRECTION searchDirection,
            String marketHashName,
            float minPrice,
            float maxPrice,
            SEARCH_OPTIONAL_CHOICE hasStickers,
            SEARCH_OPTIONAL_CHOICE isStatTrack
    ) throws IOException, UnsuccessfulRequestException {

        String url = getShopUrl().concat(
                String.format("api/v1/get_inventory_on_sale/" +
                                "?api_key=%s&code=%s" +
                                "&page=%d&sort_by=%s&order=%s" +
                                "&market_hash_name=%s" +
                                "&min_price=%f&max_price=%f" +
                                "&has_stickers=%s" +
                                "&is_stattrak=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY),
                        pageNumber, SearchEnums.getEnumValue(searchChoice),
                        SearchEnums.getEnumValue(searchDirection),
                        URLEncoder.encode(marketHashName, "UTF-8").replaceAll("\\+", "%20"), minPrice, maxPrice,
                        SearchEnums.getEnumValue(hasStickers),
                        SearchEnums.getEnumValue(isStatTrack)
                )
        );
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        List<BitSkinsItem> bitSkinsItemList = new ArrayList<>();
        JsonNode prices = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = prices.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItem item =
                    defaultObjectMapper.readValue(node.toString(), BitSkinsItem.class);
            bitSkinsItemList.add(item);
        }

        return bitSkinsItemList;
    }

    /**
     * @return current bitskins buy history
     */
    public HashMap<Long, BitSkinsHistoryEvent> getBuyHistory(int page) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_buy_history/?api_key=%s&code=%s&page=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), page));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Long, BitSkinsHistoryEvent> eventHistory = new HashMap<>();

        JsonNode items = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = items.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsHistoryEvent event =
                    defaultObjectMapper.readValue(node.toString(),
                            BitSkinsHistoryEvent.class);
            eventHistory.put(event.getItemId(), event);
        }

        return eventHistory;
    }

    /**
     * @return current bitskins sell history
     */
    public HashMap<Long, BitSkinsHistoryEvent> getSellHistory(int page) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_sell_history/?api_key=%s&code=%s&page=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), page));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Long, BitSkinsHistoryEvent> eventHistory = new HashMap<>();

        JsonNode items = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = items.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsHistoryEvent event =
                    defaultObjectMapper.readValue(node.toString(),
                            BitSkinsHistoryEvent.class);
            eventHistory.put(event.getItemId(), event);
        }

        return eventHistory;
    }

    /**
     * @return current bitskins item sell history
     */
    public HashMap<Long, BitSkinsItemSellEvent> getItemSellHistory(String itemName) throws IOException,
            UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_sales_info/?api_key=%s&code=%s&page=1&market_hash_name=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), URLEncoder.encode(itemName, "UTF-8")
                                .replaceAll("\\+", "%20")));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Long, BitSkinsItemSellEvent> eventHistory = new HashMap<>();

        JsonNode items = jsonNode.get("data").get("sales");
        Iterator<JsonNode> iterator = items.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItemSellEvent event =
                    defaultObjectMapper.readValue(node.toString(),
                            BitSkinsItemSellEvent.class);
            eventHistory.put(event.getSoldAt(), event);
        }

        return eventHistory;
    }

    /**
     * @return current bitskins inventory history
     */
    public HashMap<Long, BitSkinsInventoryEvent> getInventoryHistory(int page) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_item_history/?api_key=%s&code=%s&page=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), page));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Long, BitSkinsInventoryEvent> eventHistory = new HashMap<>();

        JsonNode items = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = items.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsInventoryEvent event =
                    defaultObjectMapper.readValue(node.toString(),
                            BitSkinsInventoryEvent.class);
            eventHistory.put(event.getItemId(), event);
        }

        return eventHistory;
    }

    /**
     * Allows you to delist an active sale item and/or re-attempt an item pending withdrawal.
     *
     * @return successful withdraws trade ids
     */
    public BitSkinsItemOperationResult withdraw(List<Long> itemIds) throws IOException, UnsuccessfulRequestException {

        if (itemIds.size() == 0) {
            return null;
        }

        StringBuilder ids = new StringBuilder();
        for (Long itemId : itemIds) {
            ids.append(itemId).append(",");
        }

        ids.deleteCharAt(ids.length() - 1);

        String url = getShopUrl().concat(
                String.format("api/v1/withdraw_item/?api_key=%s&code=%s&item_ids=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), ids.toString()));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        return defaultObjectMapper.readValue(jsonNode.get("data").toString(),
                BitSkinsItemOperationResult.class);
    }

    /**
     * @return successfully buyed item id
     */
    public BitSkinsItemOperationResult buyItem(Long itemId, Float price) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/buy_item/?api_key=%s&code=%s&item_ids=%d&prices=%.2f",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), itemId, price));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        return defaultObjectMapper.readValue(jsonNode.get("data").toString(),
                BitSkinsItemOperationResult.class);

    }


    public static final int MAX_ITEMS_PER_REQUEST = 10;

    /**
     * @return sold item ids
     */
    public BitSkinsItemOperationResult listItemsForSale(HashMap<Long, Float> itemsForSale)
            throws IOException, UnsuccessfulRequestException {

        if (itemsForSale.size() > MAX_ITEMS_PER_REQUEST) {
            throw new UnsuccessfulRequestException(
                    String.format("Too many items for sale, %d is max", MAX_ITEMS_PER_REQUEST));
        } else if (itemsForSale.size() == 0) {
            throw new UnsuccessfulRequestException("There are 0 items in request");
        }

        StringBuilder itemIds = new StringBuilder();
        StringBuilder prices = new StringBuilder();
        for (Map.Entry<Long, Float> entry : itemsForSale.entrySet()) {
            itemIds.append(entry.getKey()).append(",");
            prices.append(String.format("%.2f", entry.getValue())).append(",");
        }
        itemIds.deleteCharAt(itemIds.length() - 1);
        prices.deleteCharAt(prices.length() - 1);

        String url = getShopUrl().concat(
                String.format("api/v1/list_item_for_sale/?api_key=%s&code=%s&item_ids=%s&prices=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), itemIds, prices));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        return defaultObjectMapper.readValue(jsonNode.get("data").toString(),
                BitSkinsItemOperationResult.class);

    }


    /**
     * @return changed items
     */
    public BitSkinsItemOperationResult modifySaleItem(HashMap<Long, Float> itemsToChange)
            throws IOException, UnsuccessfulRequestException {

        if (itemsToChange.size() > MAX_ITEMS_PER_REQUEST) {
            throw new UnsuccessfulRequestException(
                    String.format("Too many items to change, %d is max", MAX_ITEMS_PER_REQUEST));
        } else if (itemsToChange.size() == 0) {
            throw new UnsuccessfulRequestException("There are 0 items in request");
        }

        StringBuilder itemIds = new StringBuilder();
        StringBuilder prices = new StringBuilder();
        for (Map.Entry<Long, Float> entry : itemsToChange.entrySet()) {
            itemIds.append(entry.getKey()).append(",");
            prices.append(String.format("%.2f", entry.getValue())).append(",");
        }
        itemIds.deleteCharAt(itemIds.length() - 1);
        prices.deleteCharAt(prices.length() - 1);

        String url = getShopUrl().concat(
                String.format("api/v1/modify_sale_item/?api_key=%s&code=%s&item_ids=%s&prices=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), itemIds, prices));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        return defaultObjectMapper.readValue(jsonNode.get("data").toString(),
                BitSkinsItemOperationResult.class);

    }

    /**
     * @return bitSkins items to delist
     */
    public List<BitSkinsItem> getResetPriceItems(int pageNumber)
            throws IOException, UnsuccessfulRequestException {

        String url = getShopUrl().concat(
                String.format("api/v1/get_reset_price_items/" +
                                "?api_key=%s&code=%s" +
                                "&page=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY),
                        pageNumber)
        );
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        List<BitSkinsItem> bitSkinsItemList = new ArrayList<>();
        JsonNode prices = jsonNode.get("data").get("items");
        Iterator<JsonNode> iterator = prices.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItem item =
                    defaultObjectMapper.readValue(node.toString(), BitSkinsItem.class);
            bitSkinsItemList.add(item);
        }

        return bitSkinsItemList;
    }


    /**
     * @return current bitskins inventory
     */
    public BitSkinsInventory getInventory(int page) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_my_inventory/?api_key=%s&code=%s&page=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), page));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        BitSkinsInventory bitSkinsInventory = new BitSkinsInventory();
        JsonNode steamItems = jsonNode.get("data").get("steam_inventory").get("items");
        JsonNode bitskinsItems = jsonNode.get("data").get("bitskins_inventory").get("items");
        JsonNode pendingItems = jsonNode.get("data").get("pending_withdrawal_from_bitskins").get("items");

        Iterator<JsonNode> iterator = steamItems.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            Iterator<JsonNode> idIterator = node.get("item_ids").elements();
            while (idIterator.hasNext()) {
                Long itemId = idIterator.next().asLong();
                BitSkinsItem item =
                        defaultObjectMapper.readValue(node.toString(),
                                BitSkinsItem.class);
                item.setItemId(itemId);
                bitSkinsInventory.getSteamInventory().put(item.getItemId(), item);
            }
        }

        iterator = bitskinsItems.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            Iterator<JsonNode> idIterator = node.get("item_ids").elements();

            int currentElement = 0;
            JsonNode correctItemNode = defaultObjectMapper.readTree(node.toString());
            while (idIterator.hasNext()) {
                ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(correctItemNode.toString());
                objectNode.set("price", node.get("prices").get(currentElement));
                objectNode.set("is_featured", node.get("is_featured").get(currentElement));
                objectNode.set("float_values", node.get("float_values").get(currentElement));
                objectNode.set("created_at", node.get("created_at").get(currentElement));
                objectNode.set("updated_at", node.get("updated_at").get(currentElement));
                objectNode.set("fraud_warnings", node.get("fraud_warnings").get(currentElement));
                currentElement++;

                Long itemId = idIterator.next().asLong();
                BitSkinsItem item =
                        defaultObjectMapper.readValue(objectNode.toString(),
                                BitSkinsItem.class);
                item.setItemId(itemId);
                bitSkinsInventory.getBitSkinsInventory().put(item.getItemId(), item);
            }
        }

        iterator = pendingItems.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            BitSkinsItem item =
                    defaultObjectMapper.readValue(node.toString(),
                            BitSkinsItem.class);
            bitSkinsInventory.getPendingWithdrawalFromBitskins().put(item.getItemId(), item);
        }

        return bitSkinsInventory;
    }

    /**
     * Allows you to cancel all buy orders for a given item name.
     *
     * @return List<Integer></Integer>
     */
    public List<Integer> cancelAllBuyOrders(String itemName) throws IOException, UnsuccessfulRequestException {
        itemName = URLEncoder.encode(itemName, "UTF-8");
        List<Integer> cancelledItems = new ArrayList<>();
        String url = getShopUrl().concat(
                String.format("api/v1/cancel_all_buy_orders/?api_key=%s&code=%s&market_hash_name=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), itemName));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        for (JsonNode node : jsonNode.get("data").get("buy_order_ids")) {
            cancelledItems.add(node.asInt());
        }
        return cancelledItems;
    }


    /**
     * Allows you to create a buy order for a single item.
     *
     * @return BitSkinsBuyOrder
     */
    public HashMap<Integer, BitSkinsBuyOrderInfo> placeBuyOrder(String itemName, Float price, int quantity) throws IOException, UnsuccessfulRequestException {
        itemName = URLEncoder.encode(itemName, "UTF-8");
        String url = getShopUrl().concat(
                String.format("api/v1/create_buy_order/?api_key=%s&code=%s&name=%s&price=%.2f&quantity=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), itemName, price, quantity));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Integer, BitSkinsBuyOrderInfo> orderInfoHashMap = new HashMap<>();
        for (JsonNode node : jsonNode.get("data").get("orders")) {
            BitSkinsBuyOrderInfo bitSkinsBuyOrderInfo = defaultObjectMapper.readValue(node.toString(),
                    BitSkinsBuyOrderInfo.class);
            orderInfoHashMap.put(bitSkinsBuyOrderInfo.getBuyOrderId(), bitSkinsBuyOrderInfo);
        }
        return orderInfoHashMap;
    }

    /**
     * Allows you to cancel an active buy order.
     *
     * @return BitSkinsBuyOrder
     */
    public BitSkinsBuyOrderInfo removeBuyOrder(Integer buyOrderId) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/cancel_buy_order/?api_key=%s&code=%s&buy_order_id=%d",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), buyOrderId));
        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        return defaultObjectMapper.readValue(jsonNode.get("data").get("order").toString(),
                BitSkinsBuyOrderInfo.class);
    }

    /**
     * Allows you to retrieve all buy orders you have placed, whether active or not.
     *
     * @return List</BitSkinsBuyOrder>
     */
    public HashMap<Integer, BitSkinsBuyOrderInfo> getBuyOrderHistory(Integer page, String itemName, String type) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_buy_order_history/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));

        if (page != null) {
            url = url.concat(String.format("&page=%d", page));
        }
        if (itemName != null && !itemName.equals("")) {
            itemName = URLEncoder.encode(itemName, "UTF-8");
            url = url.concat(String.format("&market_hash_name=%s", itemName));
        }
        if (type != null && !type.equals("")) {
            url = url.concat(String.format("&type=%s", type));
        }

        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Integer, BitSkinsBuyOrderInfo> bitSkinsBuyOrderInfos = new HashMap<>();
        for (JsonNode node : jsonNode.get("data").get("orders")) {
            BitSkinsBuyOrderInfo bitSkinsBuyOrderInfo = defaultObjectMapper.readValue(node.toString(),
                    BitSkinsBuyOrderInfo.class);
            bitSkinsBuyOrderInfos.put(bitSkinsBuyOrderInfo.getBuyOrderId(), bitSkinsBuyOrderInfo);
        }
        return bitSkinsBuyOrderInfos;
    }

    /**
     * Allows you to retrieve information about items requested/sent in a given trade from BitSkins.
     * Trade details will be unretrievable 7 days after the initiation of the trade..
     *
     * @return BitSkinsTradeDetail
     */
    public BitSkinsTradeDetail getTradeDetails(String tradeToken, String tradeId) throws IOException,
            UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_trade_details/?api_key=%s&code=%s&trade_token=%s&trade_id=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY), tradeToken, tradeId));

        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);

        BitSkinsTradeDetail tradeDetail = defaultObjectMapper.readValue(
                jsonNode.get("data").toString(), BitSkinsTradeDetail.class);

        return tradeDetail;
    }

    /**
     * Allows you to retrieve all buy orders that have not been cancelled or settled.
     * Deprecated, use get_buy_order_history.
     *
     * @return HashMap<Integer, BitSkinsBuyOrder>
     */
    @Deprecated
    public HashMap<Integer, BitSkinsBuyOrderInfo> getActiveBuyOrders(Integer page) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_active_buy_orders/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));

        if (page != null) {
            url = url.concat(String.format("&page=%d", page));
        }

        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Integer, BitSkinsBuyOrderInfo> bitSkinsBuyOrderInfos = new HashMap<>();
        for (JsonNode node : jsonNode.get("data").get("orders")) {
            BitSkinsBuyOrderInfo bitSkinsBuyOrderInfo = defaultObjectMapper.readValue(node.toString(),
                    BitSkinsBuyOrderInfo.class);
            bitSkinsBuyOrderInfos.put(bitSkinsBuyOrderInfo.getBuyOrderId(), bitSkinsBuyOrderInfo);
        }
        return bitSkinsBuyOrderInfos;
    }

    /**
     * Allows you to retrieve all market orders by all buyers
     * (except your own) that need fulfillment.
     *
     * @return HashMap<Integer, BitSkinsBuyOrder>
     */
    @Deprecated
    public HashMap<Integer, BitSkinsBuyOrderInfo> getMarketBuyOrders(Integer page, String itemName) throws IOException, UnsuccessfulRequestException {
        String url = getShopUrl().concat(
                String.format("api/v1/get_market_buy_orders/?api_key=%s&code=%s",
                        API_KEY, OtpGenerator.generatePassword(SECRET_KEY)));

        if (page != null) {
            url = url.concat(String.format("&page=%d", page));
        }
        if (itemName != null && !itemName.equals("")) {
            url = url.concat(String.format("&market_hash_name=%s", itemName));
        }

        JsonNode jsonNode = getJsonResponse(url);
        isRequestSuccessful(jsonNode);
        HashMap<Integer, BitSkinsBuyOrderInfo> bitSkinsBuyOrderInfos = new HashMap<>();
        for (JsonNode node : jsonNode.get("data").get("orders")) {
            BitSkinsBuyOrderInfo bitSkinsBuyOrderInfo = defaultObjectMapper.readValue(node.toString(),
                    BitSkinsBuyOrderInfo.class);
            bitSkinsBuyOrderInfos.put(bitSkinsBuyOrderInfo.getBuyOrderId(), bitSkinsBuyOrderInfo);
        }
        return bitSkinsBuyOrderInfos;
    }
}
