package qelit.a5cards;

import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Qelit on 23.10.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Card> data;

    public MyAdapter(List<Card> data) {
        this.data = data;
    }

    //определение класса из списка
    @Override
    public int getItemViewType(int position) {
        Card card = data.get(position);
        if (Card1.class.isInstance(card)) return 1;
        if (Card2.class.isInstance(card)) return 2;
        if (Card3.class.isInstance(card)) return 3;
        if (Card4.class.isInstance(card)) return 4;
        if (Card5.class.isInstance(card)) return 5;
        throw  new IllegalArgumentException("Unknown element");
    }
    //по числу из getItemViewType создаем ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder vh;
        switch (viewType){
            case 1: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card1, parent, false);
                vh = new ViewHolderOne(view);
                break;
            case 2: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2, parent, false);
                vh = new ViewHolderTwo(view);
                break;
            case 3: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card3, parent, false);
                vh = new ViewHolderThree(view);
                break;
            case 4: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card4, parent, false);
                vh = new ViewHolderFour(view);
                break;
            case 5: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card5, parent, false);
                vh = new ViewHolderFive(view);
                break;
            default: vh = null;
        }
        return vh;
    }
    //Заполняем все поля во всех карточках
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (Card1.class.isInstance(data.get(position))){
            Card1 card1 = (Card1) data.get(position);
            ((ViewHolderOne)holder).id.setText(String.valueOf(card1.getId()));
            ((ViewHolderOne)holder).title.setText(card1.getTitle());
            ((ViewHolderOne)holder).body.setText(card1.getBody());
            ((ViewHolderOne)holder).et1.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 100)});
            ((ViewHolderOne)holder).btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(((ViewHolderOne)holder).et1.getText().toString());
                    ((ViewHolderOne)holder).et1.setText("");
                    App.getPostsApi().getPostsByNum(a).enqueue(new Callback<Card1>() {
                        @Override
                        public void onResponse(Call<Card1> call, Response<Card1> response) {
                            data.set(0, response.body());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Card1> call, Throwable t) {
                        }
                    });
                }
            });
        }
        if (Card2.class.isInstance(data.get(position))){
            Card2 card2 = (Card2) data.get(position);
            ((ViewHolderTwo)holder).id2.setText(String.valueOf(card2.getId()));
            ((ViewHolderTwo)holder).name2.setText(card2.getName());
            ((ViewHolderTwo)holder).email2.setText(card2.getEmail());
            ((ViewHolderTwo)holder).body2.setText(card2.getBody());
            ((ViewHolderTwo)holder).et2.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 500)});
            ((ViewHolderTwo)holder).btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(((ViewHolderTwo)holder).et2.getText().toString());
                    ((ViewHolderTwo)holder).et2.setText("");
                    App.getCommentsApi().getCommentsByNum(a).enqueue(new Callback<Card2>() {
                        @Override
                        public void onResponse(Call<Card2> call, Response<Card2> response) {
                            data.set(1, response.body());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Card2> call, Throwable t) {
                        }
                    });
                }
            });
        }
        if (Card3.class.isInstance(data.get(position))){
            Card3 card3 = (Card3) data.get(position);
            ((ViewHolderThree)holder).id3.setText(String.valueOf(card3.getId()));
            ((ViewHolderThree)holder).name3.setText(card3.getName());
            ((ViewHolderThree)holder).username3.setText(card3.getUsername());
            ((ViewHolderThree)holder).email3.setText(card3.getEmail());
            ((ViewHolderThree)holder).street3.setText(card3.getAddress().getStreet());
            ((ViewHolderThree)holder).suite3.setText(card3.getAddress().getSuite());
            ((ViewHolderThree)holder).city3.setText(card3.getAddress().getCity());
            ((ViewHolderThree)holder).zipcode3.setText(card3.getAddress().getZipcode());
            ((ViewHolderThree)holder).lat3.setText(card3.getAddress().getGeo().getLat());
            ((ViewHolderThree)holder).lng3.setText(card3.getAddress().getGeo().getLng());
            ((ViewHolderThree)holder).phone3.setText(card3.getPhone());
            ((ViewHolderThree)holder).website3.setText(card3.getWebsite());
            ((ViewHolderThree)holder).c_name3.setText(card3.getCompany().getName());
            ((ViewHolderThree)holder).catchPhrase3.setText(card3.getCompany().getCatchPhrase());
            ((ViewHolderThree)holder).bs3.setText(card3.getCompany().getBs());
            ((ViewHolderThree)holder).et3.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 10)});
            ((ViewHolderThree)holder).btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(((ViewHolderThree)holder).et3.getText().toString());
                    ((ViewHolderThree)holder).et3.setText("");
                    App.getUsersApi().getUserssByNum(a).enqueue(new Callback<Card3>() {
                        @Override
                        public void onResponse(Call<Card3> call, Response<Card3> response) {
                            data.set(2, response.body());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Card3> call, Throwable t) {
                        }
                    });
                }
            });
        }
        if (Card4.class.isInstance(data.get(position))){
            Card4 card4 = (Card4) data.get(position);
            ((ViewHolderFour)holder).id4.setText(String.valueOf(card4.getId()));
            ((ViewHolderFour)holder).title4.setText(card4.getTitle());
            Picasso.with(((ViewHolderFour)holder).itemView.getContext()) //передаем контекст приложения
                    .load( card4.getUrl()) //адрес изображения
                    .into(((ViewHolderFour)holder).img4); //ссылка на ImageView
            ((ViewHolderFour)holder).et4.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 5000)});
            ((ViewHolderFour)holder).btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(((ViewHolderFour)holder).et4.getText().toString());
                    ((ViewHolderFour)holder).et4.setText("");
                    App.getPhotosApi().getPhotosByNum(a).enqueue(new Callback<Card4>() {
                        @Override
                        public void onResponse(Call<Card4> call, Response<Card4> response) {
                            data.set(3, response.body());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Card4> call, Throwable t) {
                        }
                    });
                }
            });
        }
        if (Card5.class.isInstance(data.get(position))){
            Card5 card5 = (Card5) data.get(position);
            ((ViewHolderFive)holder).id5.setText(String.valueOf(card5.getId()));
            ((ViewHolderFive)holder).title5.setText(card5.getTitle());
            if (card5.getCompleted() == true){
                ((ViewHolderFive)holder).completed5.setText("Completed!");
            }
            else
                ((ViewHolderFive)holder).completed5.setText("not implemented.");
            ((ViewHolderFive)holder).et5.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 5000)});
            ((ViewHolderFive)holder).btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(((ViewHolderFive)holder).et5.getText().toString());
                    ((ViewHolderFive)holder).et5.setText("");
                    App.getTodosApi().getTodosByNum(a).enqueue(new Callback<Card5>() {
                        @Override
                        public void onResponse(Call<Card5> call, Response<Card5> response) {
                            data.set(4, response.body());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Card5> call, Throwable t) {
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    // Классы ViewHolder для каждой карточки
    public class ViewHolderOne extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView body;
        EditText et1;
        Button btn1;

        public ViewHolderOne(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id1);
            title = (TextView) itemView.findViewById(R.id.title1);
            body = (TextView) itemView.findViewById(R.id.body1);
            et1 = (EditText) itemView.findViewById(R.id.et1);
            btn1 = (Button) itemView.findViewById(R.id.btn1);
        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView id2;
        TextView name2;
        TextView email2;
        TextView body2;
        EditText et2;
        Button btn2;
        public ViewHolderTwo(View itemView) {
            super(itemView);
            id2 = (TextView) itemView.findViewById(R.id.id2);
            name2 = (TextView) itemView.findViewById(R.id.name2);
            email2 = (TextView) itemView.findViewById(R.id.email2);
            body2 = (TextView) itemView.findViewById(R.id.body2);
            et2 = (EditText) itemView.findViewById(R.id.et2);
            btn2 = (Button) itemView.findViewById(R.id.btn2);
        }
    }

    public class ViewHolderThree extends RecyclerView.ViewHolder {
        TextView id3;
        TextView name3;
        TextView username3;
        TextView email3;
        TextView street3;
        TextView suite3;
        TextView city3;
        TextView zipcode3;
        TextView lat3;
        TextView lng3;
        TextView phone3;
        TextView website3;
        TextView c_name3;
        TextView catchPhrase3;
        TextView bs3;
        EditText et3;
        Button btn3;
        public ViewHolderThree(View itemView) {
            super(itemView);
            id3 = (TextView) itemView.findViewById(R.id.id3);
            name3 = (TextView) itemView.findViewById(R.id.name3);
            username3 = (TextView) itemView.findViewById(R.id.username3);
            email3 = (TextView) itemView.findViewById(R.id.email3);
            street3 = (TextView) itemView.findViewById(R.id.street3);
            suite3 = (TextView) itemView.findViewById(R.id.suite3);
            city3 = (TextView) itemView.findViewById(R.id.city3);
            zipcode3 = (TextView) itemView.findViewById(R.id.zipcode3);
            lat3 = (TextView) itemView.findViewById(R.id.lat3);
            lng3 = (TextView) itemView.findViewById(R.id.lng3);
            phone3 = (TextView) itemView.findViewById(R.id.phone3);
            website3 = (TextView) itemView.findViewById(R.id.website3);
            c_name3 = (TextView) itemView.findViewById(R.id.c_name3);
            catchPhrase3 = (TextView) itemView.findViewById(R.id.catchPhrase3);
            bs3 = (TextView) itemView.findViewById(R.id.bs3);
            et3 = (EditText) itemView.findViewById(R.id.et3);
            btn3 = (Button) itemView.findViewById(R.id.btn3);
        }
    }

    public class ViewHolderFour extends RecyclerView.ViewHolder {
        TextView id4;
        TextView title4;
        ImageView img4;
        EditText et4;
        Button btn4;
        public ViewHolderFour(View itemView) {
            super(itemView);
            id4 = (TextView) itemView.findViewById(R.id.id4);
            title4 = (TextView) itemView.findViewById(R.id.title4);
            img4 = (ImageView) itemView.findViewById(R.id.img4);
            et4 = (EditText) itemView.findViewById(R.id.et4);
            btn4 = (Button) itemView.findViewById(R.id.btn4);
        }
    }

    public class ViewHolderFive extends RecyclerView.ViewHolder {
        TextView id5;
        TextView title5;
        TextView completed5;
        EditText et5;
        Button btn5;
        public ViewHolderFive(View itemView) {
            super(itemView);
            id5 = (TextView) itemView.findViewById(R.id.id5);
            title5 = (TextView) itemView.findViewById(R.id.title5);
            completed5 = (TextView) itemView.findViewById(R.id.completed5);
            et5 = (EditText) itemView.findViewById(R.id.et5);
            btn5 = (Button) itemView.findViewById(R.id.btn5);
        }
    }
}
