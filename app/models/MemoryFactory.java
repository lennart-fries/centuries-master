package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;

import play.db.Database;

@Singleton
public class MemoryFactory {
    @Inject
    Database db;

    public class Memory {
        private Map<String, String> cards;
        private String openOne;
        private String openTwo;

        public Memory(Map<String, String> cards) {
            this.cards = cards;
        }

        /**
         * Opens the specified Card
         *
         * @param open the card that should be opened
         * @return true if two cards are opened
         * @throws IllegalStateException if more than two cards are being opened
         */
        public boolean openCard(String open) {
            if (openOne.equals("x")) {
                openOne = open;
                if (openTwo.equals("x")) {
                    return false;
                } else {
                    return true;
                }
            } else if (openTwo.equals("x")) {
                openTwo = open;
                if (openOne.equals("x")) {
                    return false;
                } else {
                    return true;
                }
            } else {
                throw new IllegalStateException("Trying to open three cards.");
            }
        }

        /**
         * Evaluates if the opened cards match each other
         *
         * @return true if the two opened cards match each other
         */
        public boolean evaluate() {
            if (openOne.equals("x") || openTwo.equals("x")) {
                throw new IllegalStateException("Only one card is open, can´t evaluate!");
            }
            boolean matchedCardsOpen = false;

            if (cards.containsKey(openOne)) {
                if (cards.get(openOne).equals(openTwo)) {
                    matchedCardsOpen = true;
                }
            } else if (cards.containsKey(openTwo)) {
                if (cards.get(openTwo).equals(openOne)) {
                    matchedCardsOpen = true;
                }
            }

            return matchedCardsOpen;
        }

        /**
         * Closes open Cards
         */
        public void resetOpenCards() {
            openOne = "x";
            openTwo = "x";
        }

        /**
         * Removes open Cards
         */
        public void removeOpenCards() {
            cards.remove(openOne);
            cards.remove(openTwo);
        }

        public List<String> getCards() {
            List<String> values = new ArrayList<>(cards.values());
            List<String> keys = new ArrayList<>(cards.keySet());
            List<String> all = new ArrayList<>();
            all.addAll(values);
            all.addAll(keys);
            return all;
        }

        public String getOpenOne() {
            return openOne;
        }

        public String getOpenTwo() {
            return openTwo;
        }

        public void setOpenOne(String openOne) {
            this.openOne = openOne;
        }

        public void setOpenTwo(String openTwo) {
            this.openTwo = openTwo;
        }

        @Override
        public String toString() {
            String s = "";
            for (Map.Entry<String, String> pair : cards.entrySet()) {
                s = s + pair.getKey() + ",";
                s = s + pair.getValue() + ",";
            }
            s = s + openOne + ",";
            s = s + openTwo;
            return s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Memory memory = (Memory) o;
            return Objects.equals(cards, memory.cards) &&
                    Objects.equals(openOne, memory.openOne) &&
                    Objects.equals(openTwo, memory.openTwo);
        }
    }

    /**
     * Creates a Memory from a String
     *
     * @param memory String containing all cards in the memory and the cards already opened
     * @return the constructed Memory
     */
    public Memory fromString(String memory) {
        String[] parts = memory.split(",");
        Map<String, String> cards = new HashMap<>();
        String cardOne = null;
        for (int i = 0; i < parts.length - 2; i++) {
            if (i % 2 == 0) {
                cardOne = parts[i];
            } else {
                cards.put(cardOne, parts[i]);
            }
        }

        Memory memoryGame = new Memory(cards);
        memoryGame.setOpenOne(parts[parts.length - 2]);
        memoryGame.setOpenTwo(parts[parts.length - 1]);

        return memoryGame;
    }

    /**
     * Constructs the Memory for the given level from the Database
     *
     * @param level the level for which to get the Memory
     * @return the constructed Memory
     * @throws SQLException if there was a problem with getting the required Data from the Database
     */
    public Memory getMemoryForLevel(int level) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT card1, card2 FROM MemoryData WHERE idCentury= " + level;
        ResultSet rs = statement.executeQuery(query);
        Map<String, String> memoryCards = new HashMap<>();
        while (rs.next()) {
            memoryCards.put(rs.getString("card1"), rs.getString("card2"));
        }
        rs.close();
        statement.close();
        con.close();
        Memory memory = new Memory(memoryCards);
        memory.setOpenOne("x");
        memory.setOpenTwo("x");
        return memory;
    }
}
