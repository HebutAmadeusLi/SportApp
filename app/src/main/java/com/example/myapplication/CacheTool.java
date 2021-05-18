package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class CacheTool {
    static public Map<String, String> account = new HashMap<String, String>();
    static public Vector<Book> signed = new Vector<Book>();
    static public Vector<Relationship> friend = new Vector<Relationship>();
    static public Vector<FriendClub> friendClub = new Vector<FriendClub>();
    static public Vector<Url> urls = new Vector<Url>();
    static public int cnt = 1;

    static public void clear() {
        cnt = 1;
        account.clear();
        signed.clear();
        friend.clear();
        friendClub.clear();
        urls.clear();
    }

    static public void CreateAccount(String email, String password) {
        if(account.containsKey(email)) {
            return;
        }
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

    static public void book(String email, String text, String type) {
        signed.add(new Book(email, (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString(), text, type));
    }

    static public Vector<Book> bookedList(String email) {
        Vector<Book> ret = new Vector<Book>();
        for(int i = 0; i < signed.size(); ++i) {
            if(signed.elementAt(i).email.equals(email)) {
                ret.add(signed.elementAt(i));
            }
        }
        return ret;
    }

    static public Vector<String> friendList(String email) {
        Vector<String> ret = new Vector<String>();
        ret.add("admin");
        for(int i = 0; i < friend.size(); ++i) {
            if(friend.elementAt(i).from.equals(email)) {
                ret.add(friend.elementAt(i).to);
            }
        }
        return ret;
    }

    static public void addFriend(String email, String user) {
        if(user.equals("admin")) {
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
        if(user.equals("admin")) {
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
        String email = LogAccount.account;
        if(email.equals("admin")) {
            return friendClub;
        }
        Vector<FriendClub> ret = new Vector<FriendClub>();
        for(int i = 0; i < friendClub.size(); ++i) {
            if(friendClub.elementAt(i).email.equals("admin")) {
                ret.add(friendClub.elementAt(i));
                continue;
            }
            for(int j = 0; j < friend.size(); ++i) {
                if(friend.elementAt(j).from.equals(email) && friend.elementAt(j).to.equals(friendClub.elementAt(i).email)) {
                    ret.add(friendClub.elementAt(i));
                    break;
                }
            }
        }
        return ret;
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
    public String text;
    public String type;
    Book(String _email, String _date, String _text, String _type) {
        email = _email;
        date = _date;
        text = _text;
        type = _type;
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
