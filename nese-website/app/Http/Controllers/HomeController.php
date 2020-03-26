<?php

namespace App\Http\Controllers;

use http\Message\Body;
use Illuminate\Http\Request;
use GuzzleHttp\Client;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        $client = new Client();
        $res = $client->get('http://localhost:8100/nese_rest_api_war/api/search', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);
        $switches = $client->get('http://localhost:8100/nese_rest_api_war/api/switches', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);
        $rooms = $client->get('http://localhost:8100/nese_rest_api_war/api/rooms', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);
        $devices = $client->get('http://localhost:8100/nese_rest_api_war/api/devices', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);
        $connections = $client->get('http://localhost:8100/nese_rest_api_war/api/portconnections', [
            'headers' => [
                'API_TOKEN' => 'thomas'
            ]
        ]);



        return view('home', ['search' => json_decode($res->getBody()),
            'switch' => json_decode($switches->getBody()),
            'room' => json_decode($rooms->getBody()),
            'device' => json_decode($devices->getBody()),
            'connection' => json_decode($connections->getBody()),
            ]);
    }


}
