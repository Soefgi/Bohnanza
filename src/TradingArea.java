import java.util.ArrayList;
import java.util.List;

public class TradingArea {

    private List<Card> offer = new ArrayList<>();
    private List<Card> withdrawal = new ArrayList<>();
    private Player tradePartner;

    void addOffer(Card card) {
        System.out.println("Added " + card.getBeanType() + " to offer.");
        offer.add(card);
    }

    void addWithdrawal(Card card) {
        if (tradePartner == null) {
            System.out.println("Cannot add withdrawal to offer. No trade partner set yet.");
            return;
        }
        withdrawal.add(card);
        System.out.println(tradePartner.getName() + " added " + card.getBeanType() + " to withdrawal.");
    }

    void addTradePartner(Player player) {
        if (tradePartner == null) {
            tradePartner = player;
            System.out.println("Added trade partner: " + tradePartner.getName());
        }
        else {
            System.out.println("Trading partner is already set.");
        }
    }

    void removeTradePartner() {
        tradePartner = null;
    }

    void completeTrade() {
        if (tradePartner == null) {
            System.out.println("No trading partner available.");
            abortTrade();
        }

        StringBuilder offers = new StringBuilder();
        for (int i = 0; i < offer.size(); i++) {
            if (i == offer.size() - 1) {
                offers.append(offer.get(i)).append(" ");
            } else {
                offers.append(offer.get(i)).append(", ");
            }
        }

        StringBuilder withdrawals = new StringBuilder();
        for (int i = 0; i < withdrawal.size(); i++) {
            if (i == withdrawal.size() - 1) {
                withdrawals.append(withdrawal.get(i));
            } else {
                withdrawals.append(withdrawal.get(i)).append(", ");
            }
        }

        System.out.println("Traded " + offers + "for " + withdrawals);
        //TODO give cards to player inventories
        resetTradingArea();
    }

    void abortTrade() {
        System.out.println("Aborting trade...");
        //TODO give cards from offer back to player
        resetTradingArea();
    }

    private void resetTradingArea() {
        offer.clear();
        withdrawal.clear();
        removeTradePartner();
    }
}
