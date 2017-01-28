package com.rafaels.mislugares;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Rafel S. Martin on 28/01/2017.
 */

public class VistaLugar extends ActionBarActivity {

    private long id;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savenInstanceState) {
        super.onCreate(savenInstanceState);
        setContentView(R.layout.vista_lugar);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = Lugares.elemento((int) id);

        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());

        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());

        TextView tipo = (TextView)findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());

        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());

        if(lugar.getTelefono() == 0){
            findViewById(R.id.telefono).setVisibility(View.GONE);

        } else {
            TextView telefono = (TextView) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }

        TextView url = (TextView) findViewById(R.id.url);
        url.setText(lugar.getUrl());

        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());

        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getTimeInstance().format(new Date(lugar.getFecha())));

        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(new Date(lugar.getFecha())));

        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                }
        );
    }

}
