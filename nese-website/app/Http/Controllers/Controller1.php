<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class Controller1 extends Controller
{
    public function saveApiData(){
        $client = new Client();
        $res = $client->request('POST', 'http://10.200.18.72:8100/nese_rest_api_war/api/rooms', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);
    }


}
