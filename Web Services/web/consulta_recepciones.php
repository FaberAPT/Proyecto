<?PHP
    $hostname = "localhost";
    $database = "id7595289_grupo_9_castuera_db";
    $username = "id7595289_siloscordoba";
    $password = "P@ssw0rd";
    
    $json = array();

    if(isset($_GET["parametro_1"]))
    {
        $control = $_GET['parametro_1'];

        $parametro_busqueda_1 = $_GET['parametro_2'];

        $conexion = mysqli_connect($hostname, $username, $password, $database);

        if($_GET['parametro_1'] == 1)
        {
            $consulta = "SELECT a. id_recepcion, a.nombre_empresa, a.matricula_camion, a.conductor_camion, a.cantidad_recepcion, a.material_recepcion, ".
                        "b.tipo_componente, a.numero_silo, a.fecha_recepcion, a.estado_recepcion, a.inspeccion_visual, a.presentacion, a.lote_proveedor, ".
                        "a.densidad, a.peso_especifico, a.comentarios FROM recepcion a INNER JOIN componente b ON (TRIM(a.material_recepcion) = ".
                        "TRIM(b.nombre_componente)) WHERE b.tipo_componente = '{$parametro_busqueda_1}' ORDER BY a.id_recepcion";
        }
        elseif($_GET['parametro_1'] == 2)
        {
            $consulta = "SELECT a. id_recepcion, a.nombre_empresa, a.matricula_camion, a.conductor_camion, a.cantidad_recepcion, a.material_recepcion, ".
                        "b.tipo_componente, a.numero_silo, a.fecha_recepcion, a.estado_recepcion, a.inspeccion_visual, a.presentacion, a.lote_proveedor, ".
                        "a.densidad, a.peso_especifico, a.comentarios FROM recepcion a INNER JOIN componente b ON (TRIM(a.material_recepcion) = ".
                        "TRIM(b.nombre_componente)) WHERE a.material_recepcion = TRIM('{$parametro_busqueda_1}') ORDER BY a.id_recepcion";

        }

        $resultado = mysqli_query($conexion, $consulta);

        while($registro = mysqli_fetch_array($resultado))
        {
            $json['recepcion'][] = $registro;
        }
            
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else
    {
        $results["success"] = 0;
		$results["message"] = 'Web Service no retorna';
		$json['recepcion'][] = $results;
		echo json_encode($json);
    }
?>

