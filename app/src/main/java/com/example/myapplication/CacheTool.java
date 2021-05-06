package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class CacheTool {
    static public Map<String, String> account = new Map<String, String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return false;
        }

        @Override
        public boolean containsValue(@Nullable Object value) {
            return false;
        }

        @Nullable
        @Override
        public String get(@Nullable Object key) {
            return null;
        }

        @Nullable
        @Override
        public String put(String key, String value) {
            return null;
        }

        @Nullable
        @Override
        public String remove(@Nullable Object key) {
            return null;
        }

        @Override
        public void putAll(@NonNull Map<? extends String, ? extends String> m) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @NonNull
        @Override
        public Collection<String> values() {
            return null;
        }

        @NonNull
        @Override
        public Set<Entry<String, String>> entrySet() {
            return null;
        }
    };
    static public Vector<Book> signed = new Vector<Book>();
    static public Vector<Relationship> friend = new Vector<Relationship>();
    static public Vector<FriendClub> friendClub = new Vector<FriendClub>();
    static public Vector<Url> urls = new Vector<Url>();
    static public int cnt = 1;

    static public void CreateAccount(String email, String password) {
        account.put(email, password);
    }

    static public boolean AccountIsIn(String email, String password) {
        return account.containsKey(email) && account.get(email).equals(password);
    }

    static public boolean isBookedToday(String email) {
        for(int i = 0; i < signed.size(); ++i) {
            if(signed.elementAt(i).email.equals(email) &&
                    signed.elementAt(i).date.equals((new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString())) {
                return true;
            }
        }
        return false;
    }

    static public void book(String email) {
        signed.add(new Book(email, (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString()));
    }

    static public Vector<String> bookedList(String email) {
        Vector<String> ret = new Vector<String>();
        for(int i = 0; i < signed.size(); ++i) {
            if(signed.elementAt(i).email.equals(email)) {
                ret.add(signed.elementAt(i).date);
            }
        }
        return ret;
    }

    static public Vector<String> friendList(String email) {
        Vector<String> ret = new Vector<String>();
        ret.add("admin@sport.com");
        for(int i = 0; i < friend.size(); ++i) {
            if(friend.elementAt(i).from.equals(email)) {
                ret.add(friend.elementAt(i).to);
            }
        }
        return ret;
    }

    static public void addFriend(String email, String user) {
        if(user.equals("admin@sport.com")) {
            return;
        }
        for(int i = 0; i < friend.size(); ++i) {
            if(friend.elementAt(i).from.equals(email) && friend.elementAt(i).to.equals(user)) {
                return;
            }
        }
        friend.add(new Relationship(email, user));
    }

    static public void deleteFriend(String email, String user) {
        if(user.equals("admin@sport.com")) {
            return;
        }
        for(int i = 0; i < friend.size(); ++i) {
            if(friend.elementAt(i).from.equals(email) && friend.elementAt(i).to.equals(user)) {
                friend.remove(i);
                return;
            }
        }
    }

    static public Vector<FriendClub> AllFriendClub() {
        return friendClub;
    }

    static public void addFriendClub(String email, String text) {
        friendClub.add(new FriendClub(cnt++, email, text));
    }

    static public void addFriendClubTalk(int id, String email, String text) {
        for(int i = 0; i < friendClub.size(); ++i) {
            if(friendClub.elementAt(i).id == id) {
                friendClub.elementAt(i).addTalk(new Talk(email, text));
            }
        }
    }

    static public Vector<Url> getAllUrl() {
        return urls;
    }

    static public void addUrl(String title, String url) {
        urls.add(new Url(title, url));
    }
}

class Url {
    public String title;
    public String url;
    Url(String _title, String _url) {
        title = _title;
        url = _url;
    }
}

class Book {
    public String email;
    public String date;
    Book(String _email, String _date) {
        email = _email;
        date = _date;
    }
}

class Relationship {
    public String from;
    public String to;
    Relationship(String _from, String _to) {
        from = _from;
        to = _to;
    }
}

class Talk {
    public String email;
    public String text;
    Talk(String _email, String _text) {
        email = _email;
        text = _text;
    }
}

class FriendClub {
    public int id;
    public String email;
    public String text;
    public Vector<Talk> talk;
    FriendClub(int _id, String _email, String _text) {
        id = _id;
        email = _email;
        text = _text;
        talk = new Vector<Talk>();
    }
    public void addTalk(Talk t) {
        talk.add(t);
    }
}
