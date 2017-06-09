// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

#pragma once

module Demo
{

interface DataCollector
{
    void registerConnection(string UID);
    void saleDone(string UID, string code);
    void shutdown(string UID);
};

};
