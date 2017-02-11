package com.rafaels.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

        //Los comento para q no de errores y al probar no tener que meterlo todo
        TextView nombre = (TextView) findViewById(R.id.nombre);
        //nombre.setText(lugar.getNombre());

        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
       //logo_tipo.setImageResource(lugar.getTipo().getRecurso());

        TextView tipo = (TextView)findViewById(R.id.tipo);
        //tipo.setText(lugar.getTipo().getTexto());

        if(lugar.getDireccion()=="") {
            findViewById(R.id.direccion).setVisibility(View.GONE);
        } else{
            TextView direccion = (TextView) findViewById(R.id.direccion);
            direccion.setText(lugar.getDireccion());
        }

        if(lugar.getTelefono() == 0){
            findViewById(R.id.telefono).setVisibility(View.GONE);

        } else {
            TextView telefono = (TextView) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }

        if(lugar.getUrl()==""){
            findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            TextView url = (TextView) findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }

        if(lugar.getComentario()==""){
            findViewById(R.id.comentario).setVisibility(View.GONE);
        } else {
            TextView comentario = (TextView) findViewById(R.id.comentario);
            comentario.setText(lugar.getComentario());
        }

        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(new Date(lugar.getFecha())));

        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(new Date(lugar.getFecha())));

        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        //Asociamos un escuchador de eventos
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }

    public void lanzarVistaBorrado(View view){

        new AlertDialog.Builder(this)
                .setTitle("Borrado de Lugar")
                .setMessage("¿Estás seguro que quieres eliminar este lugar?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Lugares.borrar((int)id);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.accion_compartir:
                return true;
            case R.id.accion_llegar:
                return true;
            case R.id.accion_editar:
                return true;
            case R.id.accion_borrar:
                lanzarVistaBorrado(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
