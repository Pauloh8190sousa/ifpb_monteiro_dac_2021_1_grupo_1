
import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Container from '@material-ui/core/Container';
import { api } from '../../services/api';


export default function CadastrarAutor() {
    //const response = await api.get("/register");
    const [nome, setNome] = useState('');
    const [bibliReference, setBibliReference] = useState('');

    const data = {
        nome,
        bibliReference
    }

    async function handleCadastrar() {
        
        await api.post("/createAuthor", data);
        alert("Autor Criado");
    }

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div >
                <form noValidate onSubmit={handleCadastrar}>
                    <Grid container spacing={4}>
                        <Grid item xs={12} sm={12}>
                            <TextField
                                autoComplete="fname"
                                name="firstName"
                                variant="outlined"
                                required
                                fullWidth
                                id="firstName"
                                label="Nome"
                                autoFocus
                                value={nome}
                                onChange={(e) => setNome(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12} sm={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="lastName"
                                label="Referência Bibliografica"
                                name="lastName"
                                autoComplete="lname"
                                value={bibliReference}
                                onChange={(e) => setBibliReference(e.target.value)}
                            />
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="button-cadastrar"
                        
                    >
                        Cadastrar
                    </Button>
                </form>
            </div>
        </Container>
    );
}